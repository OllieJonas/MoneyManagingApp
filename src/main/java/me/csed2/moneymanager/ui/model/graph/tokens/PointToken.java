package me.csed2.moneymanager.ui.model.graph.tokens;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.ui.model.graph.Coordinate;
import me.csed2.moneymanager.utils.ConsoleColour;

@AllArgsConstructor @Getter @Setter
public class PointToken implements GraphToken {

    private int dataX;
    private int dataY;

    @Override
    public Coordinate getCoords() {
        return null;
    }

    @Override
    public char getToken() {
        return 'x';
    }

    @Override
    public ConsoleColour getColour() {
        return ConsoleColour.RESET;
    }
}
