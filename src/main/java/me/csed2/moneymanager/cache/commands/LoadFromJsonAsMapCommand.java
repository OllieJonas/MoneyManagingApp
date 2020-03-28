package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.main.Main;
import me.csed2.moneymanager.main.Setting;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.function.Supplier;

public class LoadFromJsonAsMapCommand<K, V extends Cacheable> implements Supplier<Map<K, V>> {

    private Gson gson;

    private Type type;

    private JsonReader reader;

    public LoadFromJsonAsMapCommand(String fileName, Class<K> keyClazz, Class<V> valueClazz) throws FileNotFoundException {
        this.gson = new Gson();

        this.type = TypeFactory.getType(keyClazz, valueClazz);

        URL url = Main.class.getClassLoader().getResource(fileName);

        if (url != null) {
            this.reader = new JsonReader(new FileReader(url.getPath()));
        }
    }

    @Override
    public Map<K, V> get() {
        return gson.fromJson(reader, type);
    }


    private static class TypeFactory {

        static Type getType(Class<?> keyClazz, Class<? extends Cacheable> valueClazz) {
            if (keyClazz == String.class && valueClazz == Setting.class) {
                return new TypeToken<Map<String, Setting<?>>>() {
                }.getType();
            } else {
                return null;
            }
        }
    }
}
