package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.csed2.moneymanager.main.Main;
import me.csed2.moneymanager.main.Setting;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Map;
import java.util.function.Supplier;

public class LoadSettingsCommand implements Supplier<LinkedHashMap<String, Setting<?>>> {

    private final Gson gson;
    private final Type type;
  
    private JsonReader reader;
    private JsonWriter writer;
    
    private URL url;
  
    public LoadSettingsCommand(String fileName) throws FileNotFoundException {

        this.gson = new Gson();
        this.type = new TypeToken<LinkedHashMap<String, Setting<?>>>(){}.getType();

        url = Main.class.getClassLoader().getResource(fileName);

        if (url != null) {
            this.reader = new JsonReader(new FileReader(url.getPath()));
            }
    }

    @Override
    public LinkedHashMap<String, Setting<?>> get() {
        return gson.fromJson(reader, type);
    }

    public void set(Map<String,Setting<?>> hmap){
        try {
            Writer writer = new FileWriter(url.getPath());
            new Gson().toJson(hmap, type, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

