package me.csed2.moneymanager.transactions;

import me.csed2.moneymanager.categories.CategoryCache;

import java.util.Date;
import java.util.List;

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

    private String categoryName;

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

    public TransactionBuilder withCategoryName(String name) {
        this.categoryName = name;
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
        return new Transaction(name, getId(), date, amount, categoryName, notes, vendor);
    }

    private int getId() {
        TransactionCache cache = TransactionCache.getInstance();
        List<Transaction> transactions = cache.readByCategory(categoryName);
        if (transactions == null) {
            return 1;
        } else {
            if(transactions.size() > 0){
                return transactions.get(transactions.size() - 1).getId() + 1;
            }else{
                return 0;
            }
        }
    }

}
