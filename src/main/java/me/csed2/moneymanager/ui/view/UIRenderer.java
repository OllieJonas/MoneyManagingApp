package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.charts.adapters.Graph;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.UINode;

public interface UIRenderer {

    void render(UINode node);

    void renderText(String message);

    void renderStage(Stage<?> stage);

    void renderGraph(Graph graph);
}
