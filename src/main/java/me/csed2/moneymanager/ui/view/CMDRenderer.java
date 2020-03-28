package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.Action;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;
import me.csed2.moneymanager.ui.model.UINode;
import me.csed2.moneymanager.utils.ConsoleUtils;
import me.csed2.moneymanager.utils.StringAlignUtils;

public class CMDRenderer implements UIRenderer {

    @Override
    public void render(UINode node) {

        if (node instanceof Action) {
            Action action = (Action) node;
            action.execute(App.getInstance());

        } else if (node instanceof StageMenu) {
            StageMenu menu = (StageMenu) node;
            Stage<?> initialStage = menu.getStages().get(0);

            menu.beginPhase();
            initialStage.executionPhase();
            initialStage.print();

        } else {
            StringAlignUtils util = new StringAlignUtils(50, StringAlignUtils.Alignment.CENTRE);
            ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP, false);
            System.out.println(util.format(node.getName()));
            System.out.print("\n");

            for (int i = 1; i <= node.getChildren().size(); i++) {
                String toPrint = i + ": " + node.getChildren().get(i - 1).getName();
                System.out.println(util.format(toPrint));
            }
            ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM);
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
