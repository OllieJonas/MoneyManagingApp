package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.Action;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;
import me.csed2.moneymanager.ui.model.UINode;
import me.csed2.moneymanager.utils.ConsoleUtils;
import me.csed2.moneymanager.utils.StringAlignUtils;

import java.util.List;

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
            printStage(initialStage);

        } else {

            StringAlignUtils util = new StringAlignUtils(50, StringAlignUtils.Alignment.CENTRE);
            ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP, false);
            System.out.println(util.format(node.getName()));
            System.out.print("\n");

            List<UINode> children = node.getChildren();

            int count = 1;
            for (UINode child : children) {
                System.out.println(util.format(count + ": " + child.getName()));
                count++;
            }
            ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM);
        }
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void renderStage(Stage<?> stage) {
        printStage(stage);
    }

    private void printStage(Stage<?> stage) {
        for (String line : stage.getText()) {
            System.out.println(line);
        }
    }
}
