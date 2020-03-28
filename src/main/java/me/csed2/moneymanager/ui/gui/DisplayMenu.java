package me.csed2.moneymanager.ui.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.gui.button.*;
import me.csed2.moneymanager.ui.gui.stage.Category.*;
import me.csed2.moneymanager.ui.gui.stage.Subscription.*;
import me.csed2.moneymanager.ui.gui.stage.Transaction.*;

public abstract class DisplayMenu implements Menu{

    //Create Static Instance for each Menu
    public static DisplayMenu currentMenu;

    public static final DisplayMenu MAIN = new DisplayButtonMain();
    public static final DisplayMenu CATEGORY = new DisplayButtonCategories();
    public static final DisplayMenu UPDATE_CATEGORY = new DisplayButtonUpdateCategory();
    public static final DisplayMenu TRANSACTION = new DisplayButtonTransactions();
    public static final DisplayMenu UPDATE_TRANSACTION = new DisplayButtonUpdateTransaction();
    public static final DisplayMenu UPDATE_SUBSCRIPTION = new DisplayButtonUpdateSubscription();
    public static final DisplayMenu SUBSCRIPTION = new DisplayButtonSubscriptions();

    public static final DisplayMenu ADD_CATEGORY = new DisplayStageAddCategory();
    public static final DisplayMenu REMOVE_CATEGORY = new DisplayStageRemoveCategory();
    public static final DisplayMenu UPDATE_CATEGORY_NAME = new DisplayStageUpdateCategoryName();
    public static final DisplayMenu UPDATE_CATEGORY_BUDGET = new DisplayStageUpdateCategoryBudget();
    
    public static final DisplayMenu LIST_TRANSACTIONS = new DisplayStageListTransactions();
    public static final DisplayMenu ADD_TRANSACTION = new DisplayStageAddTransaction();
    public static final DisplayMenu REMOVE_TRANSACTION = new DisplayStageRemoveTransaction();
    public static final DisplayMenu UPDATE_TRANSACTION_NAME = new DisplayStageUpdateTransactionName();
    public static final DisplayMenu UPDATE_TRANSACTION_AMOUNT = new DisplayStageUpdateTransactionAmount();
    public static final DisplayMenu UPDATE_TRANSACTION_VENDOR = new DisplayStageUpdateTransactionVendor();
    public static final DisplayMenu UPDATE_TRANSACTION_NOTES = new DisplayStageUpdateTransactionNotes();

    public static final DisplayMenu LIST_SUBSCRIPTIONS = new DisplayStageListSubscriptions();
    public static final DisplayMenu ADD_SUBSCRIPTION = new DisplayStageAddSubscription();
    public static final DisplayMenu REMOVE_SUBSCRIPTION = new DisplayStageRemoveSubscription();
    public static final DisplayMenu UPDATE_SUBSCRIPTION_NAME = new DisplayStageUpdateSubscriptionName();
    public static final DisplayMenu UPDATE_SUBSCRIPTION_AMOUNT = new DisplayStageUpdateSubscriptionAmount();
    public static final DisplayMenu UPDATE_SUBSCRIPTION_VENDOR = new DisplayStageUpdateSubscriptionVendor();
    public static final DisplayMenu UPDATE_SUBSCRIPTION_NOTES = new DisplayStageUpdateSubscriptionNotes();

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
        App.getInstance().openMenu(parent);
    }

    protected void showMessage(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
