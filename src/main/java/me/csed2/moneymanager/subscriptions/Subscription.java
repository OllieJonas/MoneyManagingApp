package me.csed2.moneymanager.subscriptions;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.Arrays;
import java.util.Date;
import java.time.format.DateTimeFormatter;
@Getter
@Setter
public class Subscription extends Transaction implements Cacheable{

    private int timeCycle;
    private Boolean cancelMeBool;
    private String timeCycleUnit;
    private String commencement;
  
    private String positiveList[]={"yes","y","true","t","affirm"};
  

    public Subscription(String name, int id, Date date, int amount, String category, Integer timeCycle, String timeCycleUnit, String[] notes, String vendor, String cancelMe, String commencement) {
        super(name, id, date, amount, category, notes, vendor);
        this.timeCycle=timeCycle;
        this.timeCycleUnit=timeCycleUnit;
        this.commencement=commencement;

        for (String i : positiveList){
            if (i.equals(cancelMe)){
                cancelMeBool=true;
            }
            else{
                cancelMeBool=false;
            }
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
        System.out.println("  Remews every " + timeCycle + " " + timeCycleUnit);
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