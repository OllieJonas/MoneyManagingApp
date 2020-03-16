package me.csed2.moneymanager.main;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.AutoSave;
import me.csed2.moneymanager.categories.CategoryCache;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.InputReader;

import java.util.concurrent.TimeUnit;

/**
 * @author Ollie
 * @since 08/03/2020
 */
public class User {


    @Getter @Setter
    private Menu previousMenu;

    @Getter @Setter
    private Menu currentMenu;

    private InputReader reader;

    private AutoSave autoSave;

    @Getter
    private static User instance;

    public User() {
        reader = new InputReader();
        reader.start();

        autoSave = new AutoSave(5, TimeUnit.MINUTES);
        autoSave.start();
        instance = this;
    }

    public void openMenu(Menu menu) {
        previousMenu = currentMenu;
        currentMenu = menu;
        menu.print();
    }

    public synchronized void exit() {
        System.out.println("Exiting program...");

        CategoryCache.getInstance().save();

        autoSave.interrupt();

        reader.interrupt();

        System.exit(0);
    }
}
