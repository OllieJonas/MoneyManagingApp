package me.csed2.moneymanager.ui.gui.stage.Transaction;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.transactions.Transaction;
import me.csed2.moneymanager.transactions.TransactionCache;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.stage.DisplayStageMenu;

import javax.swing.*;
import java.util.Optional;

public class DisplayStageListTransactions extends DisplayStageMenu {

    public DisplayStageListTransactions(){
        super(300, 300, "List Transactions", TRANSACTION);
    }

    @Override
    protected void beginPhase(){

    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which category would you like to list the names for?"));
    }

    @Override
    public void exitPhase() {
        CategoryCache cache = CategoryCache.getInstance();
        String result = (String) stages.get(0).getResult();

        Optional<Category> category = cache.searchFirst(cat -> cat.getName().equalsIgnoreCase(result));

        if (category.isPresent()) {
            JOptionPane.showMessageDialog(null, getTransactionReport(result));
        } else {
            System.out.println("Error: Unable to find this category!");
        }
        openPreviousMenu();
    }

    private String getTransactionReport(String category){
        StringBuilder builder = new StringBuilder();

        for(Transaction transaction : TransactionCache.getInstance().search(trans -> trans.getCategory().equalsIgnoreCase(category))){
            builder.append(transaction.toFormattedString()).append("\n");
        }

        return builder.toString();
    }
}
