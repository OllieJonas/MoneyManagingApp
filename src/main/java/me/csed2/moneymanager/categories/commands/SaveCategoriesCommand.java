package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.ICommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveCategoriesCommand extends CategoriesCommand implements ICommand<Void> {

    private ArrayList<Category> categories;

    public SaveCategoriesCommand(String fileName, ArrayList<Category> categories) throws FileNotFoundException {
        super(fileName);
        this.categories = categories;
    }

    @Override
    public Void execute() {

        String gsonString = gson.toJson(categories);

        try {

            System.out.println(fileUrl.getPath());
            Path path = Paths.get(fileUrl.toURI());
            File myFile = path.toFile();

            if (myFile.delete()) {
                myFile.createNewFile();
            }

            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);

            myOutWriter.append(gsonString);

            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
