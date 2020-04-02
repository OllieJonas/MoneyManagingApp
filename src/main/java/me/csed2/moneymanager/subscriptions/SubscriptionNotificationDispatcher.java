package me.csed2.moneymanager.subscriptions;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.subscriptions.commands.ListSubscriptionsCommand;

import java.awt.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;

import me.csed2.moneymanager.ui.view.UIRenderer;
import me.csed2.moneymanager.utils.Notifications;
import me.csed2.moneymanager.transactions.commands.AddTransactionCommand;
import me.csed2.moneymanager.main.App;
import org.w3c.dom.Text;

import javax.swing.*;


public class SubscriptionNotificationDispatcher implements Runnable{
    @Getter
    @Setter
    private Boolean exitClause=false;
    private App app;
    UIRenderer renderer;
    public SubscriptionNotificationDispatcher(App app, UIRenderer renderer){
        this.app=app;
        this.renderer=renderer;
    }

    private void hangThread(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Date stringToDate(String inDate){
        Date dateFormat=null;
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy").parse(inDate);
            return dateFormat;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat;

    }

    @Override
    public void run(){
        Date earliest=null;
        System.out.println("!");
        Date nextRenewal=null;
        Date currentDate = new Date();
        Date testDate;
        Calendar calendar = Calendar.getInstance();

        ArrayList<Integer> notifiedID = new ArrayList<>();
        while(!exitClause){
            try {
                ArrayList<Subscription> list = app.getSubscriptionCache().getList();
                for(Subscription i : list){
                    if(i.getCancelMeBool()){
                        Date commencment=stringToDate(i.getCommencement());
                        calendar.setTime(commencment);
                        String cycleUnit=i.getTimeCycleUnit();
                        int cycle = i.getTimeCycle();

                        if(cycleUnit.contains("month")){
                            calendar.add(Calendar.MONTH, cycle);
                            nextRenewal=calendar.getTime();
                        }
                        else if (cycleUnit.contains("day")){
                            calendar.add(Calendar.DAY_OF_MONTH, cycle);
                            nextRenewal=calendar.getTime();
                        }
                        else if (cycleUnit.contains("year")){
                            calendar.add(Calendar.YEAR, cycle);
                            nextRenewal=calendar.getTime();
                        }
                        if(nextRenewal.compareTo(currentDate)<0 && !notifiedID.contains(i.getId())){
                            renderer.renderText(i.getName() + " renewed at a cost of £" + i.getAmount());
                            notifiedID.add(i.getId());
                            new AddTransactionCommand(i.getCategory(), i.getName(),i.getAmount(),i.getVendor(), i.getNotes());
                            System.out.println(nextRenewal);
                            i.setCommencement(new SimpleDateFormat("dd/MM/yyyy").format(nextRenewal));
                        }
                        calendar.setTime(currentDate);
                        calendar.add(Calendar.DAY_OF_MONTH,1);
                        testDate=calendar.getTime();

                        if(testDate.compareTo(nextRenewal)>0 && !notifiedID.contains(i.getId())){
                            renderer.renderText(i.getName() + " will renew tomorrow at a cost of £" + i.getAmount());
                            notifiedID.add(i.getId());
                        }
                    }
                }
            }catch(NullPointerException e){
                // Empty list returned
            }
        }
    }
}
