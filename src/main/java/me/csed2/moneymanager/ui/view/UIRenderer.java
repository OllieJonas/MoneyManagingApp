package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.UINode;
import me.csed2.moneymanager.ui.model.graph.Graph;

public interface UIRenderer {

    void render(UINode node);

    void renderText(String message);

    void renderStage(Stage<?> stage);

    void renderGraph(Graph graph);
}
