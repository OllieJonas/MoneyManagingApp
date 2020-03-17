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
    private String name;
    /**
     * Its associated ID
     */
    private int id;

    /**
     * Date transaction occurred
     */
    private Date date;

    /**
     * How much the transaction cost
     */
    private int amount;

    /**
     * The category in which the transaction belongs
     */
    private String category;

    /**
     * Any notes the user may have about the transaction
     */
    private String[] notes;

    private String vendor;

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
