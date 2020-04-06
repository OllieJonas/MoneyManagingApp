package me.csed2.moneymanager.budget;


import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.transactions.Transaction;

@Getter
@Setter
/**
 * this builds the budget objects that are stored in the budget store
 */
public class BudgetBuilder {

    private String name;

    private int budget;

    private int totalSpent;

    private int monthFor;

    private static CachedList<Category> item = App.getInstance().getCategoryCache();

    /**
     * assigns values to variables in the budget
     * @param name
     */
    BudgetBuilder(String name) {
        this.name = name;
        this.budget = item.search(name).get().getBudget();
        this.totalSpent = Checker(this.name);
        this.monthFor = item.search(name).get().getCreated().getMonth();
    }

    //This gets the transactions in a category
    public static int Checker(String budgetToCheck){
        int catID = item.search(budgetToCheck).get().getId();
        return addTrans(catID);
    }

    //This adds all the transactions in a category to the budget object
    public static int addTrans(int catID) {
        int tranSum = 0;
        for (Transaction item : App.getInstance().getTransactionCache().asList()) {
            if (item.getId() == catID) {
                tranSum += item.getAmount();
            }
        }
        return tranSum;
    }
}


