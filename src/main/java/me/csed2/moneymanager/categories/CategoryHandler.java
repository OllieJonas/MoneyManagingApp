package me.csed2.moneymanager.categories;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.Gson;
import me.csed2.moneymanager.categories.commands.LoadCategoriesCommand;
import me.csed2.moneymanager.command.CommandCallback;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.TransactionBuilder;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CategoryHandler {

    private ArrayList<Category> categories;

    public CategoryHandler() {
        categories = new ArrayList<>();
    }

    public ListenableFuture<ArrayList<Category>> loadCategories() {
        ListenableFuture<ArrayList<Category>> future = null;
        try {
            future = CommandDispatcher.getInstance().dispatchAsync(new LoadCategoriesCommand("data.json"), new FutureCallback<>() {

                @Override
                public void onSuccess(ArrayList<Category> result) {
                    categories = result;
                }

                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("Error: Unable to load categories!");
                    throwable.printStackTrace();
                }
            }, 1000, TimeUnit.SECONDS);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return future;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * Prints an example entry for the data.json file.
     * Used for showing the formatting required.
     */
    private void printExample() {
        ArrayList<Category> categories = new ArrayList<>();
        Gson gson = new Gson();

        Category category = new CategoryBuilder("Fun")
                .withId(1)
                .withCreationDate("03/03/2020")
                .withBudget(10000)
                .addTransaction(new TransactionBuilder("Score")
                        .withId(1)
                        .withDate("03/03/2020")
                        .withAmount(100)
                        .withCategory("Fun")
                        .withNotes("Test", "Test2")
                        .withVendor("SU Bath")
                        .build())
                .build();

        categories.add(category);
        System.out.println(gson.toJson(categories));
    }

    /**
     * Used for testing
     *
     * @param args null
     */
    public static void main(String[] args) {
        new CategoryHandler();
    }
}
