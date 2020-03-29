package me.csed2.moneymanager.ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import me.csed2.moneymanager.main.App;

public abstract class DisplayMenu {

    //Create Static Instance for each Menu
    public static DisplayMenu currentMenu;

    //Parent Menu
    protected DisplayMenu parent;

    //Frame information
    protected int width, height;
    protected String title;

    //Frame
    protected JFrame frame;
    protected JPanel panel;



    protected void initFrame(){
        frame = new JFrame(title);

        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit program on close
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //Center on screen
        frame.setVisible(false);


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Border
        Border blackBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black);
        panel.setBorder(blackBorder);

        frame.add(panel);

        addTitle(title);
    }

    /** Place a Title Label for this menu.
     * @param title The string to display as the title.
     */
    protected void addTitle(String title){
        JLabel labTitle = new JLabel(title);
        labTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //Place title in the middle
        labTitle.setFont(new Font("TimesRoman", Font.BOLD, 26));
        panel.add(labTitle);
    }

    public void print(){
        frame.setVisible(true);
        if(currentMenu != null){
            currentMenu.close();
        }
        currentMenu = this;
    }

    public void close(){
        frame.setVisible(false);
    }


    protected void openPreviousMenu(){
        //App.getInstance().openMenu(parent);
    }

    protected void showMessage(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
