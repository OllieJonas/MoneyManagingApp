package me.csed2.moneymanager.categories;

import com.google.gson.Gson;
import me.csed2.moneymanager.transactions.TransactionBuilder;

import java.util.ArrayList;

public class CategoryHandler {

    public CategoryHandler() {
        new CategoryRepository();
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
                        .withDate("03/03/2020")
                        .withAmount(100)
                        .withCategoryID(1)
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
