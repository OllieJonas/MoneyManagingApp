package me.csed2.moneymanager.categories.commands;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.main.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;

public abstract class CategoriesCommand {

    protected Gson gson;

    protected URL fileUrl;

    protected JsonReader reader;

    public CategoriesCommand(String fileName) throws FileNotFoundException {
        this.gson = new Gson();
        this.fileUrl = Main.class.getClassLoader().getResource(fileName);

        if (fileUrl != null) {
            reader = new JsonReader(new FileReader(fileUrl.getPath()));
        }
    }
}
