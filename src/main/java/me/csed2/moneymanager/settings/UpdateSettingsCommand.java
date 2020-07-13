package me.csed2.moneymanager.settings;

import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.main.SettingWrapper;

import java.io.FileNotFoundException;

public class UpdateSettingsCommand implements Command<Boolean> {

    private final String renderer;
    private final String test;

    private SettingWrapper wrapper;

    public UpdateSettingsCommand(String renderer, String test) {
        this.renderer = renderer;
        this.test = test;

        try {
            wrapper = new SettingWrapper("settings.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean execute(App app) {
        wrapper.save(renderer, test);
        return true;
    }
}
