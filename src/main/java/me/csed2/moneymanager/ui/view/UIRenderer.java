package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.charts.adapters.Chart;
import me.csed2.moneymanager.charts.adapters.ChartImpl;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.UINode;

public interface UIRenderer {

    void render(UINode node);

    void renderText(String message);

    void renderStage(Stage<?> stage);

    void renderGraph(Chart graph);
}
