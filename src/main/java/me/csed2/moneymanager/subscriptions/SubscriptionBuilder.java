package me.csed2.moneymanager.subscriptions;

import me.csed2.moneymanager.main.App;

import java.util.Date;

/**
 * Builder class for a Subscription.
 *
 * Example usage would be:
 *
 * Subscription trans = new SubscriptionBuilder("Score").withId(1).withDate(03/03/2020).withAmount(200)
 * .withCategory(Category.FUN).withNotes("we do love to see this").withVendor("SU").build();
 *
 * @since 03/03/2020
 */
public class SubscriptionBuilder {

    /**
     * Name of the Subscription
     */
    private String name;

    /**
     * Date Subscription occurred
     */
    private Date date;

    /**
     * How much the Subscription cost
     */
    private int amount;

    /**
     * Any notes the user may have about the Subscription
     */
    private String[] notes;

    /**
     * The name of the vendor
     */
    private String vendor;

    private String categoryName;

    public SubscriptionBuilder(String name) {
        this.name = name;
    }

    public SubscriptionBuilder withAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public SubscriptionBuilder withDate(Date date) {
        this.date = date;
        return this;
    }

    public SubscriptionBuilder withCategoryName(String name) {
        this.categoryName = name;
        return this;
    }

    public SubscriptionBuilder withNotes(String... notes) {
        this.notes = notes;
        return this;
    }

    public SubscriptionBuilder withVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    public Subscription build() {
        return new Subscription(name, App.getInstance().getSubscriptionCache().nextId(), date, amount, categoryName, notes, vendor);
    }

}
