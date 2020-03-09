package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.ui.IMenu;
import me.csed2.moneymanager.ui.cmdline.option.Option;

import java.util.LinkedList;
import java.util.List;

public abstract class CMDMenu implements IMenu {

    private List<Option> options = new LinkedList<>();

    protected void addOption(Option option) {
        options.add(option);
    }

    protected void addOptions() {

    }

    @Override
    public IMenu open() {
        for (int i = 1; i <= options.size(); i++) {
            System.out.println(i + ": " + options.get(i).getName());
        }
        return this;
    }
}
