package me.csed2.moneymanager.subscriptions;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.commands.AddTransactionCommand;
import me.csed2.moneymanager.ui.view.UIRenderer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SubscriptionNotificationDispatcher implements Runnable {

    @Getter @Setter
    private Boolean exitClause = false;

    private App app;

    private UIRenderer renderer;

    public SubscriptionNotificationDispatcher(App app, UIRenderer renderer){
        this.app = app;
        this.renderer = renderer;
    }

    @Override
    public void run(){
        Date nextRenewal;
        Date currentDate = new Date();
        Date testDate;
        Calendar calendar = Calendar.getInstance();

        ArrayList<Integer> notifiedID = new ArrayList<>();

        while(!exitClause) {
            try {
                List<Subscription> list = app.getSubscriptionCache().asImmutableList();
                for (Subscription i : list) {
                    if (i.getCancelMeBool()) {
                        Date commencement = stringToDate(i.getCommencement());
                        String cycleUnit = i.getTimeCycleUnit();
                        int cycle = i.getTimeCycle();

                        if (commencement != null) {

                            calendar.setTime(commencement);

                            if (cycleUnit.contains("month")) {
                                calendar.add(Calendar.MONTH, cycle);
                            } else if (cycleUnit.contains("day")) {
                                calendar.add(Calendar.DAY_OF_MONTH, cycle);
                            } else if (cycleUnit.contains("year")) {
                                calendar.add(Calendar.YEAR, cycle);
                            }

                            nextRenewal = calendar.getTime();

                            if (nextRenewal.compareTo(currentDate) < 0 && !notifiedID.contains(i.getId())) {

                                renderer.renderText(i.getName() + " renewed at a cost of £" + i.getAmount());

                                notifiedID.add(i.getId());

                                CommandDispatcher.dispatchSync(new AddTransactionCommand(i.getCategory(), i.getName(), i.getAmount(), i.getVendor(), i.getNotes()));

                                i.setCommencement(new SimpleDateFormat("dd/MM/yyyy").format(nextRenewal));
                            }
                            calendar.setTime(currentDate);
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                            testDate = calendar.getTime();

                            if (testDate.compareTo(nextRenewal) > 0 && !notifiedID.contains(i.getId())) {
                                renderer.renderText(i.getName() + " will renew tomorrow at a cost of £" + i.getAmount());
                                notifiedID.add(i.getId());
                            }
                        }
                    }
                }
            } catch (NullPointerException ignored) {
                // Empty list returned
            }
        }
    }

    private Date stringToDate(String inDate){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(inDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
