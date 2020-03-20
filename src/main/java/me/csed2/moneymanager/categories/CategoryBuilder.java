package me.csed2.moneymanager.categories;

import java.util.Date;

@SuppressWarnings("unused")
public class CategoryBuilder {

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

    public CategoryBuilder(String name) {
        this.name = name;
    }

    public CategoryBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public CategoryBuilder withCreationDate(Date date) {
        this.created = date;
        return this;
    }

    public CategoryBuilder withBudget(int budget) {
        this.budget = budget;
        return this;
    }

    public Category build() {
        return new Category(name, id, created, budget);
    }
}
