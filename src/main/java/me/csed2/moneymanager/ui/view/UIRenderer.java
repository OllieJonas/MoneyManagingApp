package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.UINode;

public interface UIRenderer {
    void render(UINode node);

    void sendMessage(String message);

    void renderStage(Stage<?> stage);
}
