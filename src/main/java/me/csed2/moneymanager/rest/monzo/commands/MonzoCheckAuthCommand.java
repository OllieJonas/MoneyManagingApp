package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.JsonObject;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.utils.JSONUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MonzoCheckAuthCommand implements ICommand<Boolean> {

    @Override
    public Boolean execute() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.monzo.com/ping/whoami")).build(); // ping whoami - returns if the client is authenticated

        try {
            HttpResponse<String> response = MonzoHttpClient.client.send(request, HttpResponse.BodyHandlers.ofString()); // send request
            String json = response.body(); // Get response from Monzo
            JsonObject object = JSONUtils.getAsJsonObject(json); // Convert to JsonObject

            return object.get("authenticated").getAsBoolean(); // Get the value from the authenticated key, convert to boolean.
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false; // This will only be called if the try block fails; if the try block fails then assume that they're not authenticated.
    }
}
