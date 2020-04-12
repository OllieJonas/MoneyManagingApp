package me.csed2.moneymanager.ui.model.graph;

import lombok.Getter;
import me.csed2.moneymanager.ui.model.graph.tokens.GenericToken;
import me.csed2.moneymanager.ui.model.graph.tokens.PointToken;
import me.csed2.moneymanager.ui.model.graph.tokens.GraphToken;
import me.csed2.moneymanager.utils.ConsoleColour;

import java.util.*;

@SuppressWarnings("WeakerAccess")
public abstract class Graph {

    protected transient GraphToken[][] graph;

    protected transient Set<GraphToken> tokens;

    @Getter
    protected final int totalHeight;

    @Getter
    protected int totalLength;

    Graph(int totalHeight, int totalLength) {
        this.totalHeight = totalHeight;
        this.totalLength = totalLength;
        this.graph = new GraphToken[totalHeight][totalLength];
        this.tokens = new HashSet<>();
    }

    Graph() {
        this(15, 200);
    }

    public abstract Graph build();

    public GraphToken[][] getGraph() {
        return graph;
    }
}
