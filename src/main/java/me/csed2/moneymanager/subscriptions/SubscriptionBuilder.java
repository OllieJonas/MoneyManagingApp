package me.csed2.moneymanager.subscriptions;

import java.util.Date;
import java.util.List;

/**
 * Builder class for a Subscription.
 *
 * Example usage would be:
 *
 * Subscription trans = new SubscriptionBuilder("Score").withId(1).withDate(03/03/2020).withAmount(200)
 * .withCategory(Category.FUN).withNotes("we do love to see this").withVendor("SU").build();
 *
 * @author Ollie
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
        return new Subscription(name, getId(), date, amount, categoryName, notes, vendor);
    }

    private int getId() {
        SubscriptionCache cache = SubscriptionCache.getInstance();
        List<Subscription> Subscriptions = cache.readByCategory(categoryName);
        if (Subscriptions == null) {
            return 1;
        } else {
            if(Subscriptions.size() > 0){
                return Subscriptions.get(Subscriptions.size() - 1).getId() + 1;
            }else{
                return 0;
            }
        }
    }

}
