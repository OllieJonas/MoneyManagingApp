package me.csed2.moneymanager.categories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.main.Main;
import me.csed2.moneymanager.transactions.TransactionBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CategoryHandler {

    private Gson gson;

    private URL fileUrl;

    private JsonReader reader;

    private ArrayList<Category> categories;

    public CategoryHandler() {
        this.gson = new Gson();
        this.fileUrl = Main.class.getClassLoader().getResource("data.json");
        categories = new ArrayList<>();
        try {
            if (fileUrl != null) {
                reader = new JsonReader(new FileReader(fileUrl.getPath()));
            }

            // printExample();
            loadCategories();

            categories.add(new CategoryBuilder("Rent")
                    .withId(2)
                    .withCreationDate("03/03/2020")
                    .withBudget(100)
                    .addTransaction(new TransactionBuilder("Fresh")
                            .withId(1)
                            .withDate("03/03/2020")
                            .withCategory("Rent")
                            .withVendor("Fresh SU Bath")
                            .withNotes("Test", "Test2")
                            .build())
                    .build());

            System.out.println(categories.get(1).getName());

            saveCategories();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCategories() {
        Type category = new TypeToken<ArrayList<Category>>(){}.getType();
        categories = gson.fromJson(reader, category);
    }

    private void saveCategories() {
        String gsonString = gson.toJson(categories);
        try {
            System.out.println(fileUrl.getPath());
            Path path = Paths.get(fileUrl.toURI());
            File myFile = path.toFile();
            if (myFile.delete()) {
                myFile.createNewFile();
                System.out.println("deleted");
            }
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
            myOutWriter.append(gsonString);
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printExample() {
        ArrayList<Category> categories = new ArrayList<>();

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

    public static void main(String[] args) {
        new CategoryHandler();
    }
}
