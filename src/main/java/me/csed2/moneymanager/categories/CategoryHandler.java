package me.csed2.moneymanager.categories;

import com.google.gson.Gson;
import me.csed2.moneymanager.categories.commands.LoadCategoriesCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.TransactionBuilder;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

public class CategoryHandler {

    public CategoryHandler() {
        try {
            new CategoryRepository().loadFromJson();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints an example entry for the data.json file.
     * Used for showing the formatting required.
     */
    @SuppressWarnings("unused")
    private void printExample() {
        ArrayList<Category> categories = new ArrayList<>();
        Gson gson = new Gson();

        Category category = new CategoryBuilder("Fun")
                .withId(1)
                .withCreationDate(new Date())
                .withBudget(10000)
                .addTransaction(new TransactionBuilder("Score")
                        .withDate(new Date())
                        .withAmount(100)
                        .withCategoryID(1)
                        .withNotes("Test", "Test2")
                        .withVendor("SU Bath")
                        .build())
                .build();

        categories.add(category);
        System.out.println(gson.toJson(categories));
    }
}
