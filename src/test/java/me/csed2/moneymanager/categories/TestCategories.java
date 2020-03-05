package me.csed2.moneymanager.categories;

import com.google.common.util.concurrent.ListenableFuture;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class TestCategories {

    @Test
    public void testCategoriesLoad_Successfully() {
        CategoryHandler handler = new CategoryHandler();
        ListenableFuture<ArrayList<Category>> future = handler.loadCategories();
        while (!future.isDone()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            fail(e);
        }
        assertFalse(handler.getCategories().isEmpty());
    }
}
