package me.csed2.moneymanager.budget;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;


//this Builds an object of the total transactions in a specific month, this means that the total spent for individual months are linked
@Getter
@Setter
public class MonthsTransactionsBuilder {

    private int monthFor;

    private int totalSpent;

    private static CachedList<Category> item = App.getInstance().getCategoryCache();

    /**
     * This builds the object for months transactions
     * @param cat
     * @param month
     */
    MonthsTransactionsBuilder(String cat, int month){
        this.monthFor = month;
        this.totalSpent = Checker(cat, this.monthFor);
    }

    //This gets the transactions in a category
    public static int Checker(String budgetToCheck, int monthFor){
        int catID = item.search(budgetToCheck).get().getId();
        return addTrans(catID, monthFor);
    }

    //This adds all the transactions in a category and month to the budget object
    public static int addTrans(int catID, int monthFor) {
        int tranSum = 0;
        for (Transaction item : App.getInstance().getTransactionCache().asList()) {
            if (item.getId() == catID && item.getDate().getMonth() == monthFor) {
                tranSum += item.getAmount();
            }
        }
        return tranSum;
    }
}
