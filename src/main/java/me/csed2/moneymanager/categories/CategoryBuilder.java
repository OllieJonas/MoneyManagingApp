package me.csed2.moneymanager.categories;

import me.csed2.moneymanager.transactions.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {

    /**
     * The name of the category
     */
    private String name;

    /**
     * The associated ID with the category
     */
    private int id;

    /**
     * Date of creation
     */
    private String created;

    /**
     * The budget the user has set for this category
     */
    private int budget;

    /**
     * The list of all transactions
     */
    private List<Transaction> transactions;

    public CategoryBuilder(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    public CategoryBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder withCreationDate(String date) {
        this.created = date;
        return this;
    }

    public CategoryBuilder withBudget(int budget) {
        this.budget = budget;
        return this;
    }

    public CategoryBuilder addTransaction(Transaction transaction) {
        transactions.add(transaction);
        return this;
    }

    public CategoryBuilder withTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public Category build() {
        return new Category(name, id, created, budget, transactions);
    }
}
