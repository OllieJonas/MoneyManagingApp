package me.csed2.moneymanager.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;

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
    protected int amount;

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
}
