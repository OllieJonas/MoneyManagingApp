package me.csed2.moneymanager.ui.gui;

import javax.swing.*;

public class DisplayExample extends Display {

    public DisplayExample(){
        super(300, 300, "Example");
    }

    /**
     * Add all widgets specific to this menu here
     */
    public void initDisplay(){
        frame.add(new JLabel("Bruh Moment"));
    }
}
