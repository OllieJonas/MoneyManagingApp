package me.csed2.moneymanager.ui.gui;

import javax.swing.*;
import java.awt.*;

import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.gui.button.*;
import me.csed2.moneymanager.ui.gui.stage.*;

public abstract class DisplayMenu implements Menu{

    //Create Static Instance for each Menu
    public static DisplayMenu currentMenu;

    public static final DisplayMenu MAIN = new DisplayButtonMain();
    public static final DisplayMenu CATEGORY = new DisplayButtonCategories();
    public static final DisplayMenu UPDATE_CATEGORY = new DisplayButtonUpdateCategory();
    public static final DisplayMenu TRANSACTION = new DisplayButtonTransactions();

    public static final DisplayMenu ADD_CATEGORY = new DisplayStageAddCategory();
    public static final DisplayMenu REMOVE_CATEGORY = new DisplayStageRemoveCategory();
    public static final DisplayMenu UPDATE_CATEGORY_NAME = new DisplayStageUpdateCategoryName();
    public static final DisplayMenu UPDATE_CATEGORY_BUDGET = new DisplayStageUpdateCategoryBudget();
    public static final DisplayMenu LIST_TRANSACTIONS = new DisplayStageListTransactions();
    public static final DisplayMenu ADD_TRANSACTION = new DisplayStageAddTransaction();
    public static final DisplayMenu REMOVE_TRANSACTION = new DisplayStageRemoveTransaction();

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
        User.getInstance().openMenu(parent);
    }
}
