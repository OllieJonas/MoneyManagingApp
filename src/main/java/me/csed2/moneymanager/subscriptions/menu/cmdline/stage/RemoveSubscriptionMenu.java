package me.csed2.moneymanager.subscriptions.menu.cmdline.stage;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.commands.RemoveSubscriptionCommand;
import me.csed2.moneymanager.subscriptions.menu.cmdline.SubscriptionMenu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

public class RemoveSubscriptionMenu extends StageMenu {

    public RemoveSubscriptionMenu(SubscriptionMenu menu) {
        super("Remove Subscription", menu);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which subscription would you like to remove?"));
    }

    @Override
    public void exitPhase() {
        String subscriptionName = (String) stages.get(0).getResult();

        if (CommandDispatcher.getInstance().dispatchSync(new RemoveSubscriptionCommand(subscriptionName))) {
            System.out.println("Subscription successfully removed!");
            openPreviousMenu();
        } else {
            System.out.println("Error: Unable to remove subscription!");
            restart();
        }
    }
}
