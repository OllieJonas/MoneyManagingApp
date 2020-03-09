package me.csed2.moneymanager.ui;

import me.csed2.moneymanager.main.User;

@FunctionalInterface
public interface IButton {
    void execute(User user);
}
