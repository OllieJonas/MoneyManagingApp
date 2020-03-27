package me.csed2.moneymanager.transactions.menu.cmdline.stage.update;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.TransactionArgType;
import me.csed2.moneymanager.transactions.commands.UpdateTransactionCommand;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

public class UpdateAmountMenu extends StageMenu {
    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param previousMenu
     */
    public UpdateAmountMenu(Menu previousMenu) {
        super("Update Vendor", previousMenu);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which transaction would you like to update?"));
        addStage(new Stage<>(Double.class, "What would you like to change the amount to?"));
    }

    @Override
    public void exitPhase() {
        String transactionName = (String) stages.get(0).getResult();
        Double amount = (Double) stages.get(1).getResult();

        if (CommandDispatcher.dispatchSync(
                new UpdateTransactionCommand<>(transactionName, TransactionArgType.AMOUNT, amount))) {
            System.out.println("Transaction successfully updated!");
            openPreviousMenu();
        } else {
            System.out.println("Error: Unable to update transaction!");
            restart();
        }
    }
}
