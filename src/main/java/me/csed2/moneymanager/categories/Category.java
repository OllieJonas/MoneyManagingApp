package me.csed2.moneymanager.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

/**
 * Category POJO.
 *
 * Note the use of Lombok for the getters and setters.
 *
 * @author Ollie
 * @since 03/03/2020
 */
@Getter
@Setter
@AllArgsConstructor
public class Category {

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
    private Calendar created;

    /**
     * The budget the user has set for this category
     */
    private int budget;

}
