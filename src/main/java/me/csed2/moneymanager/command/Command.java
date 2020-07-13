package me.csed2.moneymanager.command;

import me.csed2.moneymanager.main.App;

public interface Command<T> {
    T execute(App app);
}
