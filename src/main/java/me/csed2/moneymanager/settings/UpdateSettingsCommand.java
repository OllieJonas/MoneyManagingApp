package me.csed2.moneymanager.settings;
import me.csed2.moneymanager.main.App;

import java.util.Optional;
import java.util.function.Function;

public class UpdateSettingsCommand <T> implements Function<App, Boolean> {
    String renderer;
    String hello;

    public UpdateSettingsCommand(String renderer, String hello){
        this.renderer=renderer;
        this.hello=hello;
    }

    @Override
    public Boolean apply(App app) {

        return true;
    }

}
