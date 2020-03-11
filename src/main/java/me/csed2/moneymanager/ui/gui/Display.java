package me.csed2.moneymanager.ui.gui;

import javax.swing.*;
import java.awt.*;

public class Display {

    //Frame
    private JFrame frame;

    //Frame information
    private int width, height;
    private String title;

    public Display(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;

        initDisplay();
    }

    /**
     * Initiate and place widgets on frame
     */
    private void initDisplay(){
        initFrame();
    }

    private void initFrame(){
        frame = new JFrame(title);

        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit program on close
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //Center on screen
        frame.setVisible(true);
    }

}
