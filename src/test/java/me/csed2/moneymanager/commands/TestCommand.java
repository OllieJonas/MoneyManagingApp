package me.csed2.moneymanager.commands;

import me.csed2.moneymanager.command.CommandDispatcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCommand {

    @Test
    public void testSyncCommand() {
        int result = CommandDispatcher.getInstance().dispatch(() -> 5);

        assertEquals(5, result);
    }

//    @Test
//    public void testAsyncCommand_Success() {
//        CommandDispatcher.getInstance().dispatchAsync(() -> {
//            try {
//                Thread.sleep(1500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 5;
//
//        }, 2000, TimeUnit.MILLISECONDS, new FutureCallback<>() {
//
//            @Override
//            public void onSuccess(@Nullable Integer result) {
//                assertEquals(5, result);
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                Assertions.fail(e);
//            }
//        });
//    }

    @Test
    public void testAsyncCommand_Failure() {

    }
}
