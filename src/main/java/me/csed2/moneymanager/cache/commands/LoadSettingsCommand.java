package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.main.Main;
import me.csed2.moneymanager.main.Setting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class LoadSettingsCommand implements Supplier<LinkedHashMap<String, Setting<?>>> {

    private final Gson gson;
    private final Type type;
    private JsonReader reader;

    public LoadSettingsCommand(String fileName) throws FileNotFoundException {
        this.gson = new Gson();

        this.type = new TypeToken<LinkedHashMap<String, Setting<?>>>(){}.getType();

        URL url = Main.class.getClassLoader().getResource(fileName);

        if (url != null) {
            this.reader = new JsonReader(new FileReader(url.getPath()));
        }
    }

    @Override
    public LinkedHashMap<String, Setting<?>> get() {
        return gson.fromJson(reader, type);
    }
}
