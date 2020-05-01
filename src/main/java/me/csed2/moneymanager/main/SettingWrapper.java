package me.csed2.moneymanager.main;

import me.csed2.moneymanager.cache.commands.LoadSettingsCommand;
import me.csed2.moneymanager.cache.commands.SaveListToDBCommand;
import me.csed2.moneymanager.cache.commands.SaveMapToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.utils.ClassUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Optional;

public class SettingWrapper {

    private String fileName;

    // Settings
    private Map<String, Setting<?>> settings;

    public SettingWrapper(String name) throws FileNotFoundException {
        this.fileName = name;
        this.settings = CommandDispatcher.dispatchSync(new LoadSettingsCommand(name));
    }

    public Optional<Setting<?>> get(String name) {
        return Optional.ofNullable(settings.get(name));
    }

    public Optional<?> getValue(String name) {
        return Optional.ofNullable(settings.get(name).getValue());
    }

    public <T> Optional<T> getValue(String settingsName, Class<T> toCast) {
        return Optional.ofNullable(ClassUtils.cast(settings.get(settingsName).getValue(), toCast));
    }
    public void set(String name, String value){

    }

    public boolean save(String renderer, String test) {
        settings.put("renderer", new Setting<>(renderer, true));
        settings.put("hello", new Setting<>(test,true));

        System.out.println(settings.get("renderer").getValue());
        try {
            new LoadSettingsCommand(fileName).set(settings);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
}
