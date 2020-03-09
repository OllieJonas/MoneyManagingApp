package me.csed2.moneymanager.ui;

import me.csed2.moneymanager.main.User;

/**
 * This interface is a functional interface which is used to determine an action that is performed upon clicking this button.
 */
@FunctionalInterface
public interface IAction {

    /**
     * What is executed upon calling this action.
     *
     * @param user The user that's executing this
     */
    void execute(User user);
}
