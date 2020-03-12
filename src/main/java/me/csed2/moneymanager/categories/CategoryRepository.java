package me.csed2.moneymanager.categories;

import lombok.Getter;
import me.csed2.moneymanager.IRepository;
import me.csed2.moneymanager.categories.commands.LoadCategoriesCommand;
import me.csed2.moneymanager.categories.commands.SaveCategoriesCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.utils.ConsoleUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CategoryRepository implements IRepository<Category, Integer> {

    private List<Category> categories;

    private static CategoryRepository instance;

    @Getter
    private BiPredicate<String, String> namePredicate = String::equalsIgnoreCase;

    @Getter
    private BiPredicate<Integer, Integer> idPredicate = Integer::equals;

    @Getter
    private BiPredicate<Category, Transaction> transactionPredicate =
            (category, transaction) -> category.getId() == transaction.getCategoryId();



    public CategoryRepository(ArrayList<Category> categories) {
        this.categories = categories;
        instance = this;
    }

    public CategoryRepository() {
        this.categories = new ArrayList<>();
        instance = this;
    }

    @Override
    public List<Category> asList() {
        return categories;
    }

    @Override
    public Category readById(Integer id) {

        for (Category category : categories) {
            if (idPredicate.test(category.getId(), id)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public void add(Category entity) {
        categories.add(entity);
    }

    @Override
    public Category update(Category entity) {
        categories.removeIf(cat -> cat.getId() == entity.getId());
        categories.add(entity);
        return entity;
    }

    @Override
    public Category remove(Category entity) {
        categories.remove(entity);
        return entity;
    }

    @Override
    public void orderById() {
        categories.sort(Comparator.comparingInt(Category::getId));
    }

    @Override
    public void setList(List<Category> repo) {
        this.categories = repo;
    }

    public void print() {
        ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP);

        for (Category category : categories) {
            System.out.println(category.toFormattedString());
        }

        ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM);
    }

    public void printNames() {
        StringBuilder builder = new StringBuilder();

        for (Category category : categories) {
            builder.append(category.getName()).append(" ");
        }

        System.out.println(builder.toString());
    }

    public List<Transaction> readTransactions(Transaction transaction) {
        List<Transaction> transactions = new ArrayList<>();

        for (Category category : categories) {
            if (transactionPredicate.test(category, transaction)) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }


    public List<Transaction> readTransactions(Category category, Predicate<? super String> predicate) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : category.getTransactions()) {

            if (predicate.test(transaction.getName())) {
                transactions.add(transaction);
            }

        }
        return transactions;
    }

    public Category readByName(String name) {
        for (Category category : categories) {
            if (namePredicate.test(category.getName(), name)) {
                return category;
            }
        }
        return null;
    }


    public Category readByName(String name, BiPredicate<? super String, ? super String> predicate) {
        for (Category category : categories) {
            if (namePredicate.and(predicate).test(category.getName(), name)) {
                return category;
            }
        }
        return null;
    }

    public void loadFromJson() throws FileNotFoundException {
        this.categories = CommandDispatcher.getInstance().dispatchSync(new LoadCategoriesCommand("data.json"));
        orderById(); // In case it's out of order... (it shouldn't be but just in case)
    }

    public void save() {
        CommandDispatcher.getInstance().dispatchSync(new SaveCategoriesCommand("data.json", categories));
    }

    public Integer nextId() {
        return categories.get(categories.size() - 1).getId() + 1;
    }

    public Integer nextTransactionID(Category category) {
        return category.getTransactions().get(category.getTransactions().size() - 1).getId() + 1;
    }

    public static CategoryRepository getInstance() {
        return instance;
    }
}
