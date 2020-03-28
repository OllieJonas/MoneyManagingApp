package me.csed2.moneymanager.main;

import lombok.Getter;
import lombok.Setter;
import me.csed2.moneymanager.cache.Cacheable;

@Getter
@Setter
public class Setting<T> implements Cacheable {

    private String name;
    private T value;
    private boolean active;

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String toFormattedString() {
        return null;
    }
}
