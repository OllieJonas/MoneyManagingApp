package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.utils.ConsoleUtils;
import me.csed2.moneymanager.utils.StringAlignUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class is responsible for building a Command Line menu.
 *
 * To create a Command Line Menu, simply extend this class, override the addButtons(); method, then use the addOption();
 * method to add any new buttons.
 */
public abstract class CMDMenu implements Menu {

    /**
     * The list of buttons inside this menu
     */
    private List<Button> buttons = new ArrayList<>();

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     */
    public CMDMenu() {
        addButtons();
        addExitButton();
    }

    /**
     * Inherited below, add all buttons in the subclass in this method.
     */
    protected abstract void addButtons();

    /**
     * Adds a button to the list of buttons.
     *
     * @param button The button to be added.
     */
    protected void addButton(Button button) {
        buttons.add(button);
    }

    /**
     * Prints the menu in a formatted fashion
     */
    @Override
    public void print() {
        StringAlignUtils util = new StringAlignUtils(50, StringAlignUtils.Alignment.CENTRE);

        ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP);

        for (int i = 1; i <= buttons.size(); i++) {
            String toPrint = i + ": " + buttons.get(i - 1).getName();
            System.out.println(util.format(toPrint));
        }

        ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM);
    }

    @Override
    public List<Button> getButtons() {
        return buttons;
    }

    private void addExitButton() {
        addButton(new Button("Exit the Application", User::exit));
    }

    public <T extends CMDMenu> void addBackButton(T parent, boolean clearConsole) {
        addButton(new Button("Go Back", user -> user.openMenu(parent), true, clearConsole));
    }
}
