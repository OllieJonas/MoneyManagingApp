package me.csed2.moneymanager.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.csed2.moneymanager.budget.Budget;
import me.csed2.moneymanager.budget.BudgetDate;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.ui.MenuList;
import me.csed2.moneymanager.ui.StageMenuList;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Ollie
 * @since 03/03/2020
 */
public class Main {

    public Main() {

        new MenuList();
        new StageMenuList();

        new App();

        new AuthServerManager();

        // Testing Frame
        App.getInstance().render(MenuList.MAIN);
        // End of Testing
    }

    public static void main(String[] args) {
        new Main();
    }
}