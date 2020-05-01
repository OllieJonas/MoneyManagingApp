package me.csed2.moneymanager.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.main.App;

import java.util.Arrays;
import java.util.Date;

/**
 * Transaction POJO.
 *
 * Note the use of Lombok generating the getters, setters and the constructor.
 *
 * @author Ollie
 * @since 03/03/2020
 */
@Getter
@Setter
@AllArgsConstructor

public class Transaction implements Cacheable {

    /**
     * Name of the transaction
     */
    protected String name;
    /**
     * Its associated ID
     */
    protected int id;

    /**
     * Date transaction occurred
     */
    protected Date date;

    /**
     * How much the transaction cost
     */
    protected double amount;

    /**
     * The category in which the transaction belongs
     */
    protected String category;

    /**
     * Any notes the user may have about the transaction
     */
    protected String[] notes;

    protected String vendor;

    @Override
    public String toFormattedString() {
        return "name: " + name + "\n" +
                "  id: " + id + "\n" +
                "  created: " + date.toString() + "\n" +
                "  amount: " + amount + "\n" +
                "  category: " + category + "\n" +
                "  vendor: " + vendor + "\n" +
                "  notes: " + Arrays.toString(notes);
    }

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
    public static class Builder {

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
        private double amount;

        /**
         * Any notes the user may have about the transaction
         */
        private String[] notes;

        /**
         * The name of the vendor
         */
        private String vendor;

        private String categoryName;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withCategoryName(String name) {
            this.categoryName = name;
            return this;
        }

        public Builder withNotes(String... notes) {
            this.notes = notes;
            return this;
        }

        public Builder withVendor(String vendor) {
            this.vendor = vendor;
            return this;
        }

        public Transaction build() {
            return new Transaction(name, App.getInstance().getTransactionCache().nextId(), date, amount, categoryName, notes, vendor);
        }

    }
}
