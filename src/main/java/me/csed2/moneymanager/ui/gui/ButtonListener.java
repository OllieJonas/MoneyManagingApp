package me.csed2.moneymanager.ui.gui;

import me.csed2.moneymanager.main.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.function.Consumer;

public class ButtonListener implements ActionListener {

    private static ButtonListener instance = new ButtonListener();

    //Buttons and their respective Actions
    private static HashMap<String, Consumer<User>> buttonsAndActions = new HashMap<>();

    private ButtonListener(){

    }

    public void actionPerformed(ActionEvent e){
        String title = ((JFrame)SwingUtilities.getRoot(((Component)e.getSource()))).getTitle(); //Get the title of the pane that this button is on.
        buttonsAndActions.get(title + ":" + e.getActionCommand()).accept(User.getInstance());
    }

    public static ButtonListener getInstance(){
        return instance;
    }

    public static HashMap<String, Consumer<User>> getButtonsAndActions(){
        return buttonsAndActions;
    }

}
