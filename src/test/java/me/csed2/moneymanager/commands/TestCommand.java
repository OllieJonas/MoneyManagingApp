package me.csed2.moneymanager.commands;

import com.google.common.util.concurrent.FutureCallback;
import javafx.scene.web.HTMLEditorSkin;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.command.ICommand;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestCommand {

    @Test
    public void testSyncCommand() {
        int result = CommandDispatcher.getInstance().dispatch(() -> 5);

        assertEquals(5, result);
    }

    @Test
    public void testAsyncCommand() {
        try {
            int result = CommandDispatcher.getInstance().dispatchAsync(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 5;
            }, 1, TimeUnit.SECONDS, new FutureCallback<>() {

                @Override
                public void onSuccess(@Nullable Integer result) {
                    assertEquals(5, result);
                }

                @Override
                public void onFailure(@Nullable Throwable throwable) {
                    assert throwable != null;
                    throwable.printStackTrace();
                }
            });

            assertEquals(5, result);

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            fail(e);
        }
    }
}
