package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.JsonObject;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.utils.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MonzoCheckAuthCommand implements ICommand<Boolean> {

    @Override
    public Boolean execute() {
        HttpGet request = new HttpGet("https://api.monzo.com/ping/whoami");
        request.addHeader("Authorization", "Bearer " + MonzoHttpClient.getAccessToken());

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {

            if (response != null) {
                HttpEntity entity = response.getEntity();
                String json = EntityUtils.toString(entity);
                System.out.println(json);
                JsonObject object = JSONUtils.getAsJsonObject(json); // Convert to JsonObject
                return object.get("authenticated").getAsBoolean(); // Get the value from the authenticated key, convert to boolean.
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // This will only be called if the try block fails; if the try block fails then assume that they're not authenticated.
    }
}
