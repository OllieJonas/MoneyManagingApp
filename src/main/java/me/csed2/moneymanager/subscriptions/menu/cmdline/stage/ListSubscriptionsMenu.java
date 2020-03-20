package me.csed2.moneymanager.subscriptions.menu.cmdline.stage;

import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.subscriptions.Subscription;
import me.csed2.moneymanager.subscriptions.commands.ListSubscriptionsCommand;
import me.csed2.moneymanager.ui.Menu;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.cmdline.stage.StageMenu;

import java.util.List;

public class ListSubscriptionsMenu extends StageMenu {

    /**
     * Constructor for CMDMenu. Responsible for adding the buttons to the list.
     *
     * @param previousMenu
     */
    public ListSubscriptionsMenu(Menu previousMenu) {
        super("List Subscriptions", previousMenu);
    }

    @Override
    public void addStages() {
        addStage(new Stage<>(String.class, "Which category would you like to list the names for?"));
    }

    @Override
    public void exitPhase() {
        String result = (String) stages.get(0).getResult();
        List<Subscription> subscriptions = CommandDispatcher.getInstance().dispatchSync(new ListSubscriptionsCommand(result));

        for (Subscription subscription : subscriptions) {
            System.out.println(subscription.toFormattedString());
        }

        openPreviousMenu();
    }
}
