package me.csed2.moneymanager.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import me.csed2.moneymanager.ui.Menu;

public abstract class Display implements Menu{

    //Create Static Instance for each Menu
    public static final Display DISPLAY_EXAMPLE = new DisplayExample();

    //Frame
    protected JFrame frame;

    //Frame information
    private int width, height;
    private String title;

    public Display(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
        initFrame();
        initDisplay();
    }

    /**
     * Initiate and place widgets on frame
     */
    public abstract void initDisplay();

    private void initFrame(){
        frame = new JFrame(title);

        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit program on close
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //Center on screen
        frame.setVisible(false);
        frame.setLayout(new FlowLayout()); //experiment with this later
    }

    public void print(){
        frame.setVisible(true);
    }

}
