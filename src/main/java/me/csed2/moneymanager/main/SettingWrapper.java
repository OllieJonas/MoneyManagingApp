package me.csed2.moneymanager.main;

import me.csed2.moneymanager.cache.commands.LoadSettingsCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.utils.ClassUtils;

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

//    public boolean save(String name) {
//        return CommandDispatcher.dispatchSync(new SaveListToDBCommand<>>(name, settings));
//    }
}
