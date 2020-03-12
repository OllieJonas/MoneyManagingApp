package me.csed2.moneymanager.ui.gui;

import me.csed2.moneymanager.ui.Button;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import me.csed2.moneymanager.ui.Menu;

public abstract class DisplayMenu implements Menu{

    //Create Static Instance for each Menu
    public static final DisplayButtonMenu DISPLAY_EXAMPLE = new DisplayExample();
    //public static final DisplayStageMenu DISPLAY_STAGE_EXAMPLE = new DisplayStageExample();

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

        frame.add(panel);

        addTitle(title);
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
}
