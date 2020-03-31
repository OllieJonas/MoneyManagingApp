package me.csed2.moneymanager.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
public class Setting<T> {

    private T value;
    private boolean active;

}
