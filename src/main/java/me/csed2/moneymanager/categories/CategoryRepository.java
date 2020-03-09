package me.csed2.moneymanager.categories;

import me.csed2.moneymanager.IRepository;
import me.csed2.moneymanager.categories.commands.LoadCategoriesCommand;
import me.csed2.moneymanager.categories.commands.SaveCategoriesCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.Transaction;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CategoryRepository implements IRepository<Category, Integer> {

    private List<Category> categories;

    private static CategoryRepository instance;

    public CategoryRepository(ArrayList<Category> categories) {
        this.categories = categories;
        instance = this;
    }

    public CategoryRepository() {
        this.categories = new ArrayList<>();
    }

    @Override
    public List<Category> asList() {
        return categories;
    }

    @Override
    public Category readById(Integer id) {
        for (Category category : categories) {
            if (category.getId() == id) {
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
        for (Category category : categories) {
            System.out.println(category.toFormattedString());
        }
    }

    public List<Transaction> readByTransaction(Transaction transaction) {
        List<Transaction> transactions = new ArrayList<>();
        for (Category category : categories) {
            for (Transaction trans : category.getTransactions()) {
                if (transaction.getCategoryId() == transaction.getCategoryId()) {
                    transactions.add(trans);
                }
            }
        }
        return transactions;
    }

    public void loadFromJson() throws FileNotFoundException {
        this.categories = CommandDispatcher.getInstance().dispatchSync(new LoadCategoriesCommand("data.json"));
        orderById(); // In case it's out of order... (it shouldn't be but just in case)
    }

    public void save() throws FileNotFoundException {
        CommandDispatcher.getInstance().dispatchSync(new SaveCategoriesCommand("data.json", categories));
    }

    public static CategoryRepository getInstance() {
        return instance;
    }
}