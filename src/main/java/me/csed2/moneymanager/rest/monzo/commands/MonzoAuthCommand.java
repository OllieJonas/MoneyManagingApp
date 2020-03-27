package me.csed2.moneymanager.rest.monzo.commands;

import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.AuthServerManager;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.rest.monzo.server.AuthMonzoServer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.function.Function;

public class MonzoAuthCommand implements Function<App, String> {

    @Override
    public String apply(App app) {
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
