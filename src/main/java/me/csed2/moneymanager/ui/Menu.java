package me.csed2.moneymanager.ui;

/**
 * Interface for any menu, either command-line or GUI.
 *
 * @author Ollie
 * @since 08/03/2020
 */
public interface Menu {
    /**
     * Opens whatever menu is responsible for this. In the case of the command-line it prints this menu, in the case of
     * a GUI it marks the menu as visible.
     */
    void print();
}
