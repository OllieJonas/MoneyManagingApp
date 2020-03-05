package me.csed2.moneymanager.command;

import com.google.common.util.concurrent.*;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * This is a singleton class which allows for the dispatching of commands in both a synchronous and asynchronous fashion.
 *
 * The synchronous portion of this is pretty self-explanatory, only calling the "execute" method inside the command.
 *
 * However, the asynchronous portion gets a bit more complicated. This works by creating a single thread which is
 * used to execute the command. It then creates a ListenableFuture {@link ListenableFuture} which allows you to tag
 * additional events on either the success or the failure of the execution of the command, using the
 * FutureCallback object and the onSuccess and onFailure methods.
 *
 * Note: This is possible due to Guava, a public library provided by Google.
 *
 * @author Ollie
 * @since 03/03/2020
 */
public class CommandDispatcher {

    private static CommandDispatcher instance;

    public static final long DEFAULT_TIMEOUT = 10L;

    static {
        instance = new CommandDispatcher();
    }

    /**
     * Dispatching a synchronous command.
     *
     * @param command The command you'd like to execute
     * @param <T> The return type of the command
     * @return The result of the command
     */
    public <T> T dispatch(ICommand<T> command) {
        return command.execute();
    }

    /**
     * Dispatching an async command.
     *
     * @param command The command you'd like to execute
     * @param timeout How long until the request times out
     * @param onSuccess The appropriate callback either on success or failure
     * @param <T> The return type of the command
     *
     * @return The result of the execution of the command.
     *
     */
    public final <T> CompletableFuture<T> dispatchAsync(ICommand<T> command, long timeout, TimeUnit timeUnit, BiConsumer<T, ? super Throwable> onSuccess, Supplier<T> onFailure) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(); // Create a new single thread
        final CompletableFuture<T> future = new CompletableFuture<>(); // Submit the request
        future.complete(command.execute());
        future.whenComplete(onSuccess);

        service.schedule(() -> {
            if (!future.isDone()) {
                future.complete(onFailure.get());
                future.cancel(true);
            }

        }, timeout, timeUnit);
        return future;
    }

//    public <T> T dispatchAsync(ICommand<T> command, long timeout, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
//        return dispatchAsync(command, timeout, timeUnit, null);
//    }
//
//    public <T> T dispatchAsync(ICommand<T> command) throws ExecutionException, InterruptedException, TimeoutException {
//        return dispatchAsync(command, DEFAULT_TIMEOUT, TimeUnit.SECONDS, null);
//    }

    public static CommandDispatcher getInstance() {
        return instance;
    }
}
