package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.budget.BudgetCachedList;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.main.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;

public class LoadBudgetsCommand implements Command<BudgetCachedList> {

    private final Gson gson;
    private final Type type;
    private JsonReader reader;

    public LoadBudgetsCommand(String fileName) throws FileNotFoundException {
        this.gson = new Gson();
        this.type = new TypeToken<BudgetCachedList>(){}.getType();

        URL url = Main.class.getClassLoader().getResource(fileName);

        if (url != null) {
            this.reader = new JsonReader(new FileReader(url.getPath()));
        }
    }

    @Override
    public BudgetCachedList execute(App app) {
        return gson.fromJson(reader, type);
    }
}
