package me.csed2.moneymanager.ui.model.graph.tokens;

import lombok.Getter;
import me.csed2.moneymanager.ui.model.graph.Coordinate;
import me.csed2.moneymanager.utils.ConsoleColour;

public class BorderToken implements GraphToken {

    private BorderType type;

    public BorderToken(BorderType type) {
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

    public enum BorderType {
        TOP_LEFT('┌'),
        TOP_RIGHT('┐'),
        BOTTOM_LEFT('└'),
        BOTTOM_RIGHT('┘'),
        VERITCAL('|'),
        HORIZONTAL('-');

        @Getter
        private char token;

        BorderType(char token) {
            this.token = token;
        }
    }
}
