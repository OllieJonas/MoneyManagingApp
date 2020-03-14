package me.csed2.moneymanager.ui.gui.button;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.gui.ButtonListener;
import me.csed2.moneymanager.ui.gui.DisplayMenu;

public abstract class DisplayButtonMenu extends DisplayMenu {

    //Buttons
    private ArrayList<Button> buttons;

    public DisplayButtonMenu(int width, int height, String title, DisplayMenu parent) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.parent = parent;

        initFrame(); //Create the Frame and Panel
        buttons = new ArrayList<Button>();
        addButtons(); //Called by Subclasses - add all buttons
        placeButtons(); //Turn those buttons into JButtons

        frame.pack();
    }
    protected abstract void addButtons();

    /**
     * Add a Button object to the buttons ArrayList.
     * @param button The Button object to add.
     */
    protected void addButton(Button button){
        buttons.add(button);
    }

    /**
     * Take all Buttons in the buttons ArrayList, and turn them into JButtons to place on the display.
     */
    private void placeButtons(){

        int counter = 1;

        for(Button b : buttons){
            JButton jButton = new JButton(counter + ": " + b.getName());
            jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            jButton.addActionListener(ButtonListener.getInstance());
            ButtonListener.getButtonsAndActions().put(title + ":" + jButton.getActionCommand(), b.getAction()); //Make a KV pair for the button's label and the action.

            panel.add(jButton);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); //Create gap between buttons
            counter++;
        }
    }

    protected void addBackButton(){
        addButton(new Button("Go Back", user -> {
            if (parent != null) {
                user.openMenu(parent);
            }
        }, true, false, true));
    }
}
