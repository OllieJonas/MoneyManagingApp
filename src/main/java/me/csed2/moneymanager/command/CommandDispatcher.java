package me.csed2.moneymanager.command;

import com.google.common.util.concurrent.*;
import lombok.NonNull;
import me.csed2.moneymanager.main.App;

import java.util.concurrent.*;
import java.util.function.*;

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

    /**
     * Dispatching a synchronous command.
     *
     * @param command The command you'd like to execute
     * @param <T> The return type of the command
     * @return The result of the command
     */
    public static <T> T dispatchSync(Supplier<T> command) {
        return command.get();
    }

    public static <T> T dispatchSync(Function<App, T> command) {
        return command.apply(App.getInstance());
    }

    public static <T, S> S dispatchSync(Function<T, S> command, T value) {
        return command.apply(value);
    }

    public static <T, S, U> U dispatchSync(BiFunction<T, S, U> command, T value1, S value2) {
        return command.apply(value1, value2);
    }

    public static <T> void dispatchSync(Consumer<T> command, T value) {
        command.accept(value);
    }

    public static void dispatchSync(Consumer<App> command) {
        command.accept(App.getInstance());
    }

    public static <T> boolean dispatchSync(Predicate<T> command, T value) {
        return command.test(value);
    }

    public static boolean dispatchSync(Predicate<App> command) {
        return command.test(App.getInstance());
    }

    /**
     * Responsible for dispatching asynchronous commands.
     *
     * @param command The command you'd like to execute
     *
     * @param <T> The return type of the command
     */
    public static <T> CompletableFuture<T> dispatchAsync(Supplier<T> command) {
        return CompletableFuture.supplyAsync(command);
    }
}
