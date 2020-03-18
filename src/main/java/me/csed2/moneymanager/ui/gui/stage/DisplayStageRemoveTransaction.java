package me.csed2.moneymanager.ui.gui.stage;

import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.transactions.commands.RemoveTransactionCommand;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;

public class DisplayStageRemoveTransaction extends DisplayStageMenu{

    public DisplayStageRemoveTransaction(){
        super(300, 300, "Remove Transaction", TRANSACTION);
    }

    @Override
    protected void beginPhase() {

    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "What category would you like to remove the transaction from?")
                .withExecutionPhase(() -> CategoryRepository.getInstance().printNames()));

        addStage(new Stage<>(String.class, "Which transaction would you like to remove?"));
    }

    @Override
    protected void exitPhase() {
        String categoryName = (String) stages.get(0).getResult();
        String transactionName = (String) stages.get(1).getResult();

        if (CommandDispatcher.getInstance().dispatchSync(new RemoveTransactionCommand(categoryName, transactionName))) {
            System.out.println("Transaction successfully removed!");
        } else {
            System.out.println("Error: Unable to remove transaction!");
        }
        openPreviousMenu();
    }
}
