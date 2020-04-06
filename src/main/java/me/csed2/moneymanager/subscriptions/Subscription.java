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
public class Subscription extends Transaction implements Cacheable{

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
    public void print(){
        System.out.println("name: " + name);
        System.out.println("  id: " + id);
        System.out.println("  created: " + date.toString());
        System.out.println("  amount: " + amount);
        System.out.println("  category: " + category);
        System.out.println("  vendor: " + vendor);
        System.out.println("  Renews every " + timeCycle + " " + timeCycleUnit);
        System.out.println("  You want to be notified for cancellation: " + cancelMeBool);
        System.out.println("  notes: ");
        for (String note : notes) {
            System.out.println("    \"" + note + "\"");
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
}