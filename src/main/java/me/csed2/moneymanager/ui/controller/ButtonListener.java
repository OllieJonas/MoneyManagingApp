package me.csed2.moneymanager.ui.controller;

import me.csed2.moneymanager.main.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.function.Consumer;

public class ButtonListener implements ActionListener {

    private static ButtonListener instance = new ButtonListener();

    //Buttons and their respective Actions
    private static HashMap<String, Consumer<App>> buttonsAndActions = new HashMap<>();

    private ButtonListener(){

    }

    public void actionPerformed(ActionEvent e){
        String title = ((JFrame)SwingUtilities.getRoot(((Component)e.getSource()))).getTitle(); //Get the title of the pane that this button is on.
        buttonsAndActions.get(title + ":" + e.getActionCommand()).accept(App.getInstance());
    }

    public static ButtonListener getInstance(){
        return instance;
    }

    public static HashMap<String, Consumer<App>> getButtonsAndActions(){
        return buttonsAndActions;
    }

}
