package me.csed2.moneymanager.command;

import com.google.common.util.concurrent.ListenableFuture;
import lombok.experimental.UtilityClass;
import me.csed2.moneymanager.main.App;

import java.util.concurrent.CompletableFuture;
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
@UtilityClass
public class CommandDispatcher {

    public <T> T dispatchSync(Command<T> command) {
        return command.execute(App.getInstance());
    }
}
