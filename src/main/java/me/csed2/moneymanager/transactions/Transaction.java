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

    protected boolean interactable;

    public Transaction(String name, int id, Date date, int amount, String category, String[] notes, String vendor) {
        this(name, id, date, amount, category, notes, vendor, true);
    }

    public void print() {
        System.out.println("name: " + name);
        System.out.println("  id: " + id);
        System.out.println("  created: " + date.toString());
        System.out.println("  amount: " + amount);
        System.out.println("  category: " + category);
        System.out.println("  vendor: " + vendor);
        System.out.println("  notes: ");
        for (String note : notes) {
            System.out.println("    \"" + note + "\"");
        }
    }

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
