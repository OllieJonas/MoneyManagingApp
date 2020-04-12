package me.csed2.moneymanager.ui.model.graph.tokens;

import lombok.Getter;
import me.csed2.moneymanager.ui.model.graph.Coordinate;
import me.csed2.moneymanager.utils.ConsoleColour;

public class AxisToken implements GraphToken {

    private AxisType type;

    public AxisToken(AxisType type) {
        this.type = type;
    }

    @Override
    public Coordinate getCoords() {
        return null;
    }

    @Override
    public char getToken() {
        return type.getToken();
    }

    @Override
    public ConsoleColour getColour() {
        return ConsoleColour.RESET;
    }

    public boolean isVertical() {
        return type == AxisType.Y;
    }

    public enum AxisType {
        X('X'),
        Y('Y');

        @Getter
        private final char token;

        AxisType(char token) {
            this.token = token;
        }
    }
}
