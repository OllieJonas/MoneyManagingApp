package me.csed2.moneymanager.categories;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;

import java.util.Date;

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
public class Category implements Cacheable {

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
    private Date created;

    /**
     * The budget the user has set for this category
     */
    private int budget;

    public String toFormattedString() {
        return "category: " + name + "  " + "\n  "
                + " id: " + id + "\n  "
                + " created: " + created + "\n  "
                + " budget: £" + Math.round(budget / 100);
    }

    @SuppressWarnings("unused")
    public static class Builder {

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
        private Date created;

        /**
         * The budget the user has set for this category
         */
        private int budget;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withCreationDate(Date date) {
            this.created = date;
            return this;
        }

        public Builder withBudget(int budget) {
            this.budget = budget;
            return this;
        }

        public Category build() {
            return new Category(name, id, created, budget);
        }
    }
}
