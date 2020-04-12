package me.csed2.moneymanager.ui.model.graph.tokens;

import me.csed2.moneymanager.ui.model.graph.Coordinate;
import me.csed2.moneymanager.utils.ConsoleColour;

public interface GraphToken {

    Coordinate getCoords();

    char getToken();

    ConsoleColour getColour();

    default String getFormatted() {
        return getColour().getConsoleCode() + getToken() + ConsoleColour.RESET.getConsoleCode();
    }
}
