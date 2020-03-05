package me.csed2.moneymanager.commands;

import com.google.common.util.concurrent.FutureCallback;
import me.csed2.moneymanager.command.CommandCallback;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.command.ICommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class TestCommand {

    @Test
    public void testSyncCommand() {
        int result = CommandDispatcher.getInstance().dispatchSync(() -> 5);
        assertEquals(5, result);
    }

    @Test
    public void testAsyncCommand_NewThread() {
        CommandDispatcher.getInstance().dispatchAsync(new Simple_Async_Command_Test(), new FutureCallback<>() {

            @Override
            public void onSuccess(Integer result) {
                fail("Executed in time");
            }

            @Override
            public void onFailure(Throwable throwable) {
                fail("Executed in time");
            }

        }, 1000, TimeUnit.MILLISECONDS);

        assertEquals(1, 1);
    }
    @Test
    public void testAsyncCommand_Success() {
        CommandDispatcher.getInstance().dispatchAsync(new Simple_Async_Command_Test(), new FutureCallback<>() {
            @Override
            public void onSuccess(Integer result) {
                assertEquals(5, result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                fail(throwable);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testAsyncCommandFailure_TimeoutException() {
        CommandDispatcher.getInstance().dispatchAsync(new Simple_Async_Command_Test(), new FutureCallback<>() {
            @Override
            public void onSuccess(Integer result) {
                fail("Command didn't fail");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable.toString());
                assertEquals(TimeoutException.class.getName(), throwable.toString());
            }
        }, 100, TimeUnit.MILLISECONDS);
    }

    private static class Simple_Async_Command_Test implements ICommand<Integer> {

        @Override
        public Integer execute() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                fail(e);
            }
            return 5;
        }
    }
}
