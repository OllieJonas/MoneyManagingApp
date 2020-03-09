package me.csed2.moneymanager.ui;

import lombok.Getter;
import me.csed2.moneymanager.main.User;

public class Option {

    private IButton button;

    @Getter
    private final String name;

    public Option(String name) {
        this.name = name;
    }

    public Option attachButton(IButton button) {
        this.button = button;
        return this;
    }

    public void execute(User user) {
        button.execute(user);
    }


}
