package me.csed2.moneymanager.command;

import com.google.common.util.concurrent.*;

import java.util.concurrent.*;

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

    public static final int DEFAULT_TIMEOUT = 10;

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
     * @param timeUnit The unit of time in which for the timeout. (eg. 10, TimeUnit.SECONDS will mean it times out in 10 seconds)
     * @param callback The appropriate callback either on success or failure
     * @param <T> The return type of the command
     *
     * @return The result of the execution of the command.
     *
     * @throws ExecutionException If the command is unable to execute
     * @throws InterruptedException If something interrupts the thread
     * @throws TimeoutException If the request times out
     */
    public final <T> T dispatchAsync(ICommand<T> command, int timeout, TimeUnit timeUnit, FutureCallback<T> callback) throws ExecutionException, InterruptedException, TimeoutException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor()); // Create a new single thread
        ListenableFuture<T> future = service.submit(command::execute); // Submit the request

        if (callback != null) {
            Futures.addCallback(future, callback, service); // Add the callback
        }

        T result = future.get(timeout, timeUnit);

        service.shutdown(); // Ensure there aren't any threads that haven't been closed properly

        return result;
    }

    public <T> T dispatchAsync(ICommand<T> command, int timeout) throws InterruptedException, ExecutionException, TimeoutException {
        return dispatchAsync(command, timeout, TimeUnit.SECONDS, null);
    }

    public <T> T dispatchAsync(ICommand<T> command, int timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return dispatchAsync(command, timeout, timeUnit, null);
    }

    public <T> T dispatchAsync(ICommand<T> command) throws InterruptedException, ExecutionException, TimeoutException {
        return dispatchAsync(command, DEFAULT_TIMEOUT, TimeUnit.SECONDS, null);
    }

    public static CommandDispatcher getInstance() {
        return instance;
    }
}
