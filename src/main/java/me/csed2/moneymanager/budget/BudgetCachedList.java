package me.csed2.moneymanager.budget;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.cache.commands.LoadBudgetsCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import org.objectweb.asm.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BudgetCachedList extends CachedList<Budget> {

    private int overallBudget;

    private Budget currentBudget = new Budget("test", 1, 100, 100, new Date(), Collections.emptySet());

    public BudgetCachedList() {

    }

    public BudgetCachedList load(String fileName) throws FileNotFoundException {
        return CommandDispatcher.dispatchSync(new LoadBudgetsCommand(fileName));
    }

    public void setOverallBudget(int overallBudget) {
        this.overallBudget = overallBudget;
    }

    public void setCurrentBudget(Budget currentBudget) {
        this.currentBudget = currentBudget;
    }

    public static void main(String[] args) {

    }
}
