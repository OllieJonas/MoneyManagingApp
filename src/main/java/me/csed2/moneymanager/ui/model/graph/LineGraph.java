package me.csed2.moneymanager.ui.model.graph;

import me.csed2.moneymanager.ui.model.graph.tokens.GenericToken;
import me.csed2.moneymanager.ui.model.graph.tokens.GraphToken;
import me.csed2.moneymanager.utils.ConsoleColour;

public class LineGraph extends Graph {

    private String title;
    private String xLabel;
    private String yLabel;

    public LineGraph(String title, String xLabel, String yLabel) {
        super();
        this.title = title;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        System.out.println("l");
    }

    @Override
    public Graph build() {
        tokens.add(new GenericToken('x', ConsoleColour.BLACK, new Coordinate(3, 3)));
        for (int y = 0; y < totalHeight; y++) {
            for (int x = 0; x < totalLength; x++) {
                boolean placed = false;
                for (GraphToken token : tokens) {
                    if (token.getCoords().equals(x, y)) {
                        graph[y][x] = token;
                        placed = true;
                        break;
                    }
                }
                if (!placed)
                    graph[y][x] = new GenericToken('-', ConsoleColour.BLACK, new Coordinate(x, y));
            }
        }
        return this;
    }
}
