package me.csed2.moneymanager.subscriptions;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.utils.StringParserFactory;

import java.util.Arrays;
import java.util.Date;

@Getter @Setter
public class Subscription extends Transaction implements Cacheable {

    private int timeCycle;
    private Boolean cancelMeBool;
    private String timeCycleUnit;
    private String commencement;

    public Subscription(String name, int id, Date date, int amount, String category, Integer timeCycle, String timeCycleUnit, String[] notes, String vendor, String cancelMe, String commencement) {
        super(name, id, date, amount, category, notes, vendor);
        this.timeCycle = timeCycle;
        this.timeCycleUnit = timeCycleUnit;
        this.commencement = commencement;

        try {
            cancelMeBool = StringParserFactory.parseBoolean(cancelMe);
        } catch (InvalidTypeException e) {
            App.getInstance().render(e.getMessage());
        }
    }

    @Override
    public String toFormattedString() {
        return "name: " + name + "\n" +
                "  Id: " + id + "\n" +
                "  Created: " + date.toString() + "\n" +
                "  Amount: " + amount/100 + "\n" +
                "  Category: " + category + "\n" +
                "  Vendor: " + vendor + "\n" +
                "  Subscription interval: " + timeCycle + " " + timeCycleUnit +"\n"+
                "  Notified upon renewal: " + cancelMeBool + "\n" +
                "  Date of last renewal: " + commencement + "\n" +
                "  Notes: " + Arrays.toString(notes);
    }

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
    public static class Builder {

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

        private int timeCycle;

        private String timeCycleUnit;

        private String categoryName;
        private String cancelMe;
        private String commencement;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withAmount(int amount) {
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
        public Builder withTimeCycle(int timeCycle){
            this.timeCycle=timeCycle;
            return this;
        }
        public Builder withTimeCycleUnit(String timeCycleUnit){
            this.timeCycleUnit=timeCycleUnit;
            return this;
        }
        public Builder withCancelMe(String cancelNe){
            this.cancelMe =cancelNe;
            return this;
        }
        public Builder withCommencement(String commencement){
            this.commencement=commencement;
            return this;
        }

        public Subscription build() {
            return new Subscription(name, App.getInstance().getSubscriptionCache().nextId(), date, amount, categoryName, timeCycle, timeCycleUnit, notes, vendor, cancelMe, commencement);
        }

    }
}