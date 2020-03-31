package me.csed2.moneymanager.main;

import me.csed2.moneymanager.cache.commands.LoadSettingsCommand;
import me.csed2.moneymanager.command.CommandDispatcher;

import java.io.FileNotFoundException;
import java.util.Map;

public class SettingWrapper {

    private String fileName;

    // Settings
    private Map<String, Setting<?>> settings;

    public SettingWrapper(String name) throws FileNotFoundException {
        this.fileName = name;
        this.settings = CommandDispatcher.dispatchSync(new LoadSettingsCommand(name));
    }

    public Setting<?> get(String name) {
        return settings.get(name);
    }

//    public boolean save(String name) {
//        return CommandDispatcher.dispatchSync(new SaveListToDBCommand<>>(name, settings));
//    }
}
