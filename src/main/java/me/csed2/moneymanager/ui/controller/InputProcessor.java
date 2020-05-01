package me.csed2.moneymanager.ui.controller;

import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.Action;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;
import me.csed2.moneymanager.ui.model.UINode;
import me.csed2.moneymanager.utils.StringParserFactory;

import java.util.List;

/**
 * This class will determine what to do based on user input from the console.
 *
 * @author Ollie
 * @since 08/03/2020
 */
public class InputProcessor {

    public static void process(App app, String input) {
        UINode node = app.getCurrentNode();

        if (node instanceof StageMenu) {
            processStage(app, input, node);
        } else {
            processMenu(app, input, node);
        }
    }

    private static void processMenu(App app, String input, UINode node) {
        try {
            int option = StringParserFactory.parseInt(input);
            List<UINode> children = node.getChildren();

            if (option <= 0 || option > children.size()) { // Checking that the user has entered a valid number
                System.out.println("Please enter a number between the given values!");
            } else {
                UINode target = children.get(option - 1);

                if (target instanceof Action) {
                    Action action = (Action) target;
                    action.execute(app);
                    //app.render(node); // Render the parent node again, you don't actually need to traverse to an action.
                } else {
                    app.render(target);
                }
            }
        } catch (InvalidTypeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processStage(App app, String input, UINode node) {
        StageMenu menu = (StageMenu) node;
        if (input.equalsIgnoreCase("exit")) {
            app.render(node.getParent());

        } else {
            Stage<?> currentStage = menu.currentStage();
            try {
                Object result = StringParserFactory.parse(input, currentStage.getResultType()); // Convert text to object
                currentStage.setResult(result); // Set result to stage

                Stage<?> nextStage = menu.nextStage();

                if (nextStage != null) {
                    app.render(nextStage);
                } else {
                    app.render(node.getParent());
                }
            } catch (InvalidTypeException e) {
                System.out.println(e.getMessage());
                app.render(currentStage);
            }
        }
    }
}
