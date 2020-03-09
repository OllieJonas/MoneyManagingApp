package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Option;

import java.util.ArrayList;
import java.util.List;

public abstract class CMDMenu extends Menu {

    private List<Option> options = new ArrayList<>();

    public CMDMenu(Menu previousMenu) {
        super(previousMenu);

        addOptions();
        addExitOption();
        open();
    }

    public abstract void addOptions();

    protected void addOption(Option option) {
        options.add(option);
    }

    @Override
    public void open() {
        for (int i = 1; i <= options.size(); i++) {
            System.out.println(i + ": " + options.get(i - 1).getName());
        }
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    private void addExitOption() {
        addOption(new Option("Exit the Application").attachButton(User::exit));
    }
}
