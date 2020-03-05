package me.csed2.moneymanager.command;

import com.google.common.util.concurrent.*;
import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
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
    public <T> T dispatchSync(ICommand<T> command) {
        return command.execute();
    }

    /**
     * Responsible for dispatching asynchronous commands.
     *
     * @param command The command you'd like to execute
     * @param callback The results of this computation
     * @param timeout The timeout
     * @param timeUnit The unit of time for the timeout
     * @param <T> The return type of the command
     */
    public final <T> ListenableFuture<T> dispatchAsync(ICommand<T> command, @NonNull FutureCallback<T> callback, long timeout, TimeUnit timeUnit) {
        ListeningScheduledExecutorService service = MoreExecutors.listeningDecorator(Executors.newSingleThreadScheduledExecutor());
        ListenableFuture<T> future = service.submit(command::execute);

        Futures.addCallback(future, callback, service);

        Futures.withTimeout(future, timeout, timeUnit, service);

        return future;
    }

    public static CommandDispatcher getInstance() {
        return instance;
    }
}
