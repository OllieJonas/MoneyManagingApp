package me.csed2.moneymanager.categories.commands;

import com.google.gson.reflect.TypeToken;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.ICommand;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoadCategoriesCommand extends CategoriesCommand implements ICommand<ArrayList<Category>> {

    public LoadCategoriesCommand(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    public ArrayList<Category> execute() {
        Type category = new TypeToken<ArrayList<Category>>(){}.getType();
        return gson.fromJson(reader, category);
    }
}
