package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.cache.CacheTypeFactory;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.main.Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

public class LoadFromDBCommand<T extends Cacheable> implements ICommand<List<T>> {

    private final Type type;

    private final Gson gson;

    private JsonReader reader;

    public LoadFromDBCommand(Class<T> klass, String fileName) throws FileNotFoundException {
        this.type = CacheTypeFactory.getType(klass);
        this.gson = new Gson();
        URL fileUrl = Main.class.getClassLoader().getResource(fileName);

        if (fileUrl != null) {
            this.reader = new JsonReader(new FileReader(fileUrl.getPath()));
        }
    }

    @Override
    public List<T> execute() {
        return gson.fromJson(reader, type);
    }
}
