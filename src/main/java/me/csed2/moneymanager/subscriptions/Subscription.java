package me.csed2.moneymanager.subscriptions;

import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.Arrays;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class Subscription extends Transaction implements Cacheable{

    private int timeCycle;
    private Boolean cancelMe;
    private Date startDate = date;

    public Subscription(String name, int id, Date date, int amount, String category, Integer timeCycle, String timeCycleUnit, String[] notes, String vendor) {
        super(name, id, date, amount, category, notes, vendor);
    }


    @Override
    public void print(){
        System.out.println("name: " + name);
        System.out.println("  id: " + id);
        System.out.println("  created: " + date.toString());
        System.out.println("  amount: " + amount);
        System.out.println("  category: " + category);
        System.out.println("  vendor: " + vendor);
        System.out.println("  Remews every " + timeCycle + " ");
        System.out.println("  You want to be notified for cancellation: " + cancelMe);
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