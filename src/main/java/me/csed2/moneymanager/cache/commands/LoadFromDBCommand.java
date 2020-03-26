package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.Main;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.subscriptions.Subscription;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * This class contains the Command to Load from a Database.
 * @param <T>
 */
public class LoadFromDBCommand<T extends Cacheable> implements Supplier<List<T>> {

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
    public List<T> get() {
        return gson.fromJson(reader, type);
    }


    /**
     * This class implements the Factory design pattern (https://refactoring.guru/design-patterns/factory-method).
     *
     * This class is used to get the type of ArrayList to map the JSON file into.
     */
    private static class CacheTypeFactory {

        /**
         * Main method. Takes the class of the cacheable item and returns a Type to be used for GSON.
         *
         * @param clazz The class.
         * @return A type containing an ArrayList version of the class.
         */
        private static Type getType(Class<? extends Cacheable> clazz) {
            if (clazz == Transaction.class) {
                return new TypeToken<ArrayList<Transaction>>(){}.getType();
            } else if (clazz == Category.class) {
                return new TypeToken<ArrayList<Category>>(){}.getType();
            } else if (clazz ==Subscription.class){
                return new TypeToken<ArrayList<Subscription>>(){}.getType();
            }  else {
                return null;
            }
        }
    }
}
