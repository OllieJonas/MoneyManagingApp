package me.csed2.moneymanager.categories.commands;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.main.Main;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SaveCategoriesCommand implements ICommand<Boolean> {

    private final Gson gson;
    private final URL fileUrl;

    private final List<Category> categories;

    public SaveCategoriesCommand(String fileName, List<Category> categories) {
        this.gson = new Gson();
        this.fileUrl = Main.class.getClassLoader().getResource(fileName);

        this.categories = categories;
    }

    @Override
    public Boolean execute() {

        String gsonString = gson.toJson(categories);

        try {

            System.out.println(fileUrl.getPath());
            Path path = Paths.get(fileUrl.toURI());
            File myFile = path.toFile();

            if (myFile.delete()) {
                myFile.createNewFile();
            }

            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

            myOutWriter.append(gsonString);

            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
