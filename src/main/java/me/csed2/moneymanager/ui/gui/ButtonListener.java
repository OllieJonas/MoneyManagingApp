package me.csed2.moneymanager.ui.gui;

import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.IAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class ButtonListener implements ActionListener {

    private static ButtonListener instance = new ButtonListener();

    //Buttons and their respective Actions - **not sure if there's a better way to identify buttons than their label?**
    private static HashMap<String, IAction> buttonsAndActions = new HashMap<String, IAction>();

    private ButtonListener(){

    }

    public void actionPerformed(ActionEvent e){
        System.out.println(e.getID());
        buttonsAndActions.get(e.getActionCommand()).execute(User.getInstance());
    }

    public static ButtonListener getInstance(){
        return instance;
    }

    public static HashMap<String, IAction> getButtonsAndActions(){
        return buttonsAndActions;
    }

}
