package me.csed2.moneymanager.rest.monzo.commands;

import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.rest.monzo.server.AuthMonzoServer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.function.Function;

public class MonzoAuthCommand implements Command<String> {

    @Override
    public String execute(App app) {
        try {
            AuthServerManager manager = AuthServerManager.getInstance();
            manager.addServer(new AuthMonzoServer(8080)); // Start the server to listen for responses from Monzo

            app.getMonzoClient().accessPage(); // Access auth webpage

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
