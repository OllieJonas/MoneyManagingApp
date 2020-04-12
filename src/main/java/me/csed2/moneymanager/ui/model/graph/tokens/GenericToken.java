package me.csed2.moneymanager.ui.model.graph.tokens;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.ui.model.graph.Coordinate;
import me.csed2.moneymanager.utils.ConsoleColour;

@Getter @Setter @AllArgsConstructor
public class GenericToken implements GraphToken {

    private char token;
    private ConsoleColour colour;
    private Coordinate coordinate;

    @Override
    public Coordinate getCoords() {
        return coordinate;
    }
}
