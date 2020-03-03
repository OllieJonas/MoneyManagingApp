package me.csed2.moneymanager.transactions;

import me.csed2.moneymanager.categories.Category;

import java.util.Calendar;

/**
 * Builder class for a transaction.
 *
 * @author Ollie
 * @since 03/03/2020
 */
public class TransactionBuilder {

    /**
     * Name of the transaction
     */
    private String name;

    /**
     * Its associated ID
     */
    private int id = -1;

    /**
     * Date transaction occurred
     */
    private Calendar date;

    /**
     * How much the transaction cost
     */
    private int amount;

    /**
     * The category in which the transaction belongs
     */
    private Category category;

    /**
     * Any notes the user may have about the transaction
     */
    private String[] notes;

    public TransactionBuilder(String name) {
        this.name = name;
    }

    public TransactionBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder withCategory(Category category) {
        this.category = category;
        return this;
    }

    public TransactionBuilder withDate(Calendar date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder withNotes(String... notes) {
        this.notes = notes;
        return this;
    }

    public Transaction build() {
        return new Transaction(name, id, date, amount, category, notes);
    }
}