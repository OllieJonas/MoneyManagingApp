package me.csed2.moneymanager.categories.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.main.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

public class LoadCategoriesCommand implements ICommand<ArrayList<Category>> {

    private final Gson gson;

    private JsonReader reader;

    public LoadCategoriesCommand(String fileName) throws FileNotFoundException {

        this.gson = new Gson();
        URL fileUrl = Main.class.getClassLoader().getResource(fileName);

        if (fileUrl != null) {
            reader = new JsonReader(new FileReader(fileUrl.getPath()));
        }

    }

    @Override
    public ArrayList<Category> execute() {
        Type category = new TypeToken<ArrayList<Category>>(){}.getType();
        return gson.fromJson(reader, category);
    }
}
