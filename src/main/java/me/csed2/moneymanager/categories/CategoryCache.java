package me.csed2.moneymanager.categories;

import me.csed2.moneymanager.cache.Cache;
import me.csed2.moneymanager.cache.commands.LoadFromDBCommand;
import me.csed2.moneymanager.cache.commands.SaveToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.utils.ConsoleUtils;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CategoryCache extends Cache<Category> {

    private static CategoryCache instance;

    public CategoryCache() {
        instance = this;
    }

    public void print() {
        ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP);

        for (Category category : items) {
            System.out.println(category.toFormattedString());
        }

        ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM);
    }

    public void printNames() {
        StringBuilder builder = new StringBuilder();

        for (Category category : items) {
            builder.append(category.getName()).append(" ");
        }

        System.out.println(builder.toString());
    }

    public void load(String fileName) throws FileNotFoundException {
        this.items = CommandDispatcher.getInstance().dispatchSync(new LoadFromDBCommand<>(Category.class, fileName));
        sort(Comparator.comparingInt(Category::getId)); // In case it's out of order... (it shouldn't be but just in case)
    }

    public void save(String fileName) {
        CommandDispatcher.getInstance().dispatchSync(new SaveToDBCommand<>(fileName, items));
    }

    public static CategoryCache getInstance() {
        return instance;
    }

    public String getCategoryReport() {
        StringBuilder report = new StringBuilder();

        for (Category category : items) {
            report.append(category.toFormattedString()).append("\n");
        }

        return report.toString();
    }
}
