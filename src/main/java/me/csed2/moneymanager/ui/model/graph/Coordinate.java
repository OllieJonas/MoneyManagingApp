package me.csed2.moneymanager.ui.model.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Coordinate {
    private int x;
    private int y;

    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }
}
