package me.csed2.moneymanager.transactions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
public class Transaction {

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
    private int categoryId;

    /**
     * Any notes the user may have about the transaction
     */
    private String[] notes;

    private String vendor;
}
