package me.csed2.moneymanager.ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.Button;

public abstract class Display implements Menu{

    //Create Static Instance for each Menu
    public static final Display DISPLAY_EXAMPLE = new DisplayExample();

    //Frame
    protected JFrame frame;
    protected JPanel panel;

    //Frame information
    private int width, height;
    private String title;

    //Buttons
    private ArrayList<Button> buttons;

    public Display(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;

        initFrame(); //Create the Frame and Panel
        addButtons(); //Called by Subclasses - add all buttons
        placeButtons(); //Turn those buttons into JButtons


        frame.pack();
    }

    private void initFrame(){
        frame = new JFrame(title);

        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit program on close
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //Center on screen
        frame.setVisible(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.add(panel);

        addTitle(title);
        buttons = new ArrayList<Button>();
    }

    /** Place a Title Label for this menu.
     * @param title The string to display as the title.
     */
    protected void addTitle(String title){
        JLabel labTitle = new JLabel(title);
        labTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //Place title in the middle
        labTitle.setFont(new Font("TimesRoman", Font.BOLD, 20));
        panel.add(labTitle);
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
            ButtonListener.getButtonsAndActions().put(jButton.getActionCommand(), b.getAction()); //Make a KV pair for the button's label and the action.

            panel.add(jButton);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); //Create gap between buttons
            counter++;
        }
    }

    public void print(){
        frame.setVisible(true);
    }


}
