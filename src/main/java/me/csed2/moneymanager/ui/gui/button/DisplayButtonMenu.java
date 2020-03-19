package me.csed2.moneymanager.ui.gui.button;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.gui.ButtonIcon;
import me.csed2.moneymanager.ui.gui.ButtonListener;
import me.csed2.moneymanager.ui.gui.DisplayMenu;

public abstract class DisplayButtonMenu extends DisplayMenu {

    //Buttons
    private ArrayList<Button> buttons;
    private ArrayList<JPanel> buttonPairPanels;
    private HashMap<Button, ButtonIcon> buttonsAndIcons;

    private boolean showIcons;

    public DisplayButtonMenu(int width, int height, String title, DisplayMenu parent, boolean showIcons) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.parent = parent;
        this.showIcons = showIcons;

        initFrame(); //Create the Frame and Panel
        buttons = new ArrayList<Button>();
        buttonsAndIcons = new HashMap<Button, ButtonIcon>();
        buttonPairPanels = new ArrayList<JPanel>();
        addButtons(); //Called by Subclasses - add all buttons
        placeButtons(); //Turn those buttons into JButtons

        frame.pack();
    }
    protected abstract void addButtons();

    /**
     * Add a Button object to the buttons ArrayList.
     * @param button The Button object to add.
     */
    protected void addButton(Button button, String imageAddress){
        buttons.add(button);
        ButtonIcon icon = new ButtonIcon(imageAddress);
        buttonsAndIcons.put(button, icon);
    }

    /**
     * Add a Button object to the buttons ArrayList.
     * @param button The Button object to add.
     */
    protected void addButton(Button button){
        buttons.add(button);
        ButtonIcon icon = new ButtonIcon("icons/button_0.png");
        buttonsAndIcons.put(button, icon);
    }

    /**
     * Take all Buttons in the buttons ArrayList, and turn them into JButtons to place on the display.
     */
    private void placeButtons(){

        int counter = 1;

        //Add first pair-panel
        JPanel firstPairPanel = new JPanel(new FlowLayout());
        buttonPairPanels.add(firstPairPanel);
        panel.add(firstPairPanel);

        for(Button b : buttons){

            JButton jButton;

            if(showIcons) {
                jButton = new JButton(new ImageIcon(buttonsAndIcons.get(b).getIcon()));
                jButton.setBorder(BorderFactory.createEmptyBorder()); //Remove border
                jButton.setContentAreaFilled(false); //Remove contents
            }else{
                jButton = new JButton(counter + ":" + b.getName());
            }

            jButton.setActionCommand(counter + ":" + b.getName());
            jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            jButton.addActionListener(ButtonListener.getInstance());
            ButtonListener.getButtonsAndActions().put(title + ":" + jButton.getActionCommand(), b.getAction()); //Make a KV pair for the button's label and the action.

            placeButtonOnPairPanel(jButton);

            panel.add(Box.createRigidArea(new Dimension(0, 10))); //Create gap between buttons
            counter++;
        }
    }

    private void placeButtonOnPairPanel(JButton jButton){
        JPanel topPanel = buttonPairPanels.get(buttonPairPanels.size() - 1);

        //Create middle gap
        if(topPanel.getComponents().length == 1){
            topPanel.add(Box.createRigidArea(new Dimension(40, 0)));
        }

        if(topPanel.getComponents().length < 3){
            topPanel.add(jButton);

        }else{
            JPanel newPanel = new JPanel();
            newPanel.setLayout(new FlowLayout());
            buttonPairPanels.add(newPanel);
            newPanel.add(jButton);
            panel.add(newPanel);
        }
    }

    protected void addBackButton(){
        addButton(new Button("Go Back", user -> {
            if (parent != null) {
                user.openMenu(parent);
            }
        }, true, false, true), "icons/button_back_0.png");
    }
}
