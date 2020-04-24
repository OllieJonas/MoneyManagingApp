package me.csed2.moneymanager.settings;

import me.csed2.moneymanager.main.SettingWrapper;

import java.io.FileNotFoundException;

public class UpdateSettingsCommand{
    String renderer;
    String test;
    SettingWrapper wrapper;
    public UpdateSettingsCommand(String renderer, String test){
        this.renderer=renderer;
        this.test=test;

        try {
            wrapper = new SettingWrapper("settings.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean update(){

        wrapper.save(renderer, test);


        return true;
    }
}
