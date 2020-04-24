package me.csed2.moneymanager.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: Get rid of active
 * @param <T>
 */
@Setter @Getter @AllArgsConstructor
public class Setting<T> {

    private T value;
    private boolean active;

}
