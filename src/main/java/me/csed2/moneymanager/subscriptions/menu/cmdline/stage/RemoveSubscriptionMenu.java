package me.csed2.moneymanager.subscriptions.menu.cmdline.stage;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.commands.RemoveSubscriptionCommand;
import me.csed2.moneymanager.subscriptions.menu.cmdline.SubscriptionMenu;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;

public class RemoveSubscriptionMenu extends StageMenu {

    public RemoveSubscriptionMenu(SubscriptionMenu menu) {
        super("Remove Subscription", menu, image);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which subscription would you like to remove?"));
    }

    @Override
    public void exitPhase() {
        String subscriptionName = (String) stages.get(0).getResult();

        if (CommandDispatcher.dispatchSync(new RemoveSubscriptionCommand(subscriptionName))) {
            System.out.println("Subscription successfully removed!");
            openPreviousMenu();
        } else {
            System.out.println("Error: Unable to remove subscription!");
            restart();
        }
    }
}
