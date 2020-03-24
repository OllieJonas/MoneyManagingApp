package me.csed2.moneymanager.ui;

import lombok.Getter;
import me.csed2.moneymanager.main.User;

import java.util.function.Consumer;

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
    @Getter
    private Consumer<User> action;

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
    public Button(String name, Consumer<User> action, boolean showMenu, boolean clearConsole, boolean surroundWithSpaces) {
        this.name = name;
        this.action = action;
        this.showMenu = showMenu;
        this.clearConsole = clearConsole;
        this.surroundWithSpaces = surroundWithSpaces;
    }

    /**
     * Constructor that calls the main constructor with the values of:
     * ClearConsole: true
     *
     * @param name Text to be printed out
     * @param action The action to be performed upon execution
     * @param showMenu Whether to reprint the menu the user is on upon completion
     * @param surroundWithSpaces Whether to surround the execution with spaces
     */
    public Button(String name, Consumer<User> action, boolean showMenu, boolean surroundWithSpaces) {
        this(name, action, showMenu, true, surroundWithSpaces);
    }

    /**
     * Constructor that calls the main constructor with values of:
     * ShowMenu: false, ClearConsole: true, SurroundWithSpaces: true
     *
     * @param name The text to be printed out
     * @param action The action to be performed upon execution
     */
    public Button(String name, Consumer<User> action) {
        this(name, action, false, true, false);
    }

    /**
     * Constructor that calls the main constructor with the values of:
     * ShowMenu: false, ClearConsole: true
     *
     * @param name Name of the button
     * @param action The action to be performed upon execution
     * @param surroundWithSpaces Whether to surround the execution with spaces
     */
    public Button(String name, Consumer<User> action, boolean surroundWithSpaces) {
        this(name, action, false, true, surroundWithSpaces);
    }

    /**
     * Executes the action inside this button.
     *
     * @param user The user executing.
     */
    public void execute(User user) {
        action.accept(user);
    }


}
