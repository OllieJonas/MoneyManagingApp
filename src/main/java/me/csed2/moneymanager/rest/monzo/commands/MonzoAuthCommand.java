package me.csed2.moneymanager.rest.monzo.commands;

import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.rest.monzo.server.AuthMonzoServer;

import java.io.IOException;
import java.net.URISyntaxException;

public class MonzoAuthCommand implements ICommand<String> {

    @Override
    public String execute() {
        try {
            AuthServerManager manager = AuthServerManager.getInstance();
            manager.addServer(new AuthMonzoServer(8080)); // Start the server to listen for responses from Monzo

            MonzoHttpClient client = new MonzoHttpClient(); // Start Monzo Client
            client.accessPage(); // Access auth webpage

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}
