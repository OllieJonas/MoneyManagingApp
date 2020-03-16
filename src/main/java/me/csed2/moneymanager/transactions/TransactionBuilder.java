package me.csed2.moneymanager.transactions;

import java.util.Date;

/**
 * Builder class for a transaction.
 *
 * Example usage would be:
 *
 * Transaction trans = new TransactionBuilder("Score").withId(1).withDate(03/03/2020).withAmount(200)
 * .withCategory(Category.FUN).withNotes("we do love to see this").withVendor("SU").build();
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
     * Date transaction occurred
     */
    private Date date;

    /**
     * How much the transaction cost
     */
    private int amount;

    /**
     * Any notes the user may have about the transaction
     */
    private String[] notes;

    /**
     * The name of the vendor
     */
    private String vendor;

    private int categoryId;

    public TransactionBuilder(String name) {
        this.name = name;
    }

    public TransactionBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public TransactionBuilder withCategoryID(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public TransactionBuilder withNotes(String... notes) {
        this.notes = notes;
        return this;
    }

    public TransactionBuilder withVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public Transaction build() {
        return new Transaction(name, getIdFromCat(), date, amount, categoryId, notes, vendor);
    }

    private int getIdFromCat() {
        return 1;
    }

}
