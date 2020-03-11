package me.csed2.moneymanager.ui;

import lombok.Getter;
import me.csed2.moneymanager.main.User;

/**
 * This class contains the implementation of an option for the command line (eg: 3. Exit the Application).
 *
 * @author Ollie
 * @since 08/03/2020
 */
public class Button {

    /**
     * The action to be performed upon selecting this button.
     */
    private IAction action;

    /**
     * The text that is printed out when the menu prints out.
     */
    @Getter
    private String name;

    /**
     * Whether the user's current menu should be reprinted after completing the action. DEFAULT: true.
     */
    @Getter
    private boolean showMenu;

    /**
     * Whether the console should be cleared before executing the action. DEFAULT: false.
     */
    @Getter
    private final boolean clearConsole;

    @Getter
    private final boolean surroundWithSpaces;

    /**
     * Default constructor for Button. Contains all fields.
     *
     * @param name The text to be printed out
     * @param action The action to be performed upon execution
     * @param showMenu Whether the menu should be shown after execution
     * @param clearConsole Whether the console should be cleared before execution
     * @param surroundWithSpaces Whether when printing the result should be surrounded with spaces
     */
    public Button(String name, IAction action, boolean showMenu, boolean clearConsole, boolean surroundWithSpaces) {
        this.name = name;
        this.action = action;
        this.showMenu = showMenu;
        this.clearConsole = clearConsole;
        this.surroundWithSpaces = surroundWithSpaces;
    }

    public Button(String name, IAction action, boolean showMenu, boolean surroundWithSpaces) {
        this(name, action, showMenu, true, surroundWithSpaces);
    }

    /**
     * Constructor that calls the main constructor with values of ShowMenu: false, ClearConsole: true.
     *
     * @param name The text to be printed out
     * @param action The action to be performed upon execution
     */
    public Button(String name, IAction action) {
        this(name, action, false, true, true);
    }

    public Button(String name, IAction action, boolean surroundWithSpaces) {
        this(name, action, false, true, surroundWithSpaces);
    }

    /**
     * Executes the action inside this button.
     *
     * @param user The user executing.
     */
    public void execute(User user) {
        action.execute(user);
    }


}
