package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.rest.monzo.client.MonzoAccount;
import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.utils.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MonzoListAccountsCommand implements ICommand<JsonArray> {

    @Override
    public JsonArray execute() {
        HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/accounts"); // Make request for accounts
        request.addHeader("Authorization", "Bearer " + MonzoHttpClient.getAccessToken()); // Add header showing access token

        try (CloseableHttpClient client = HttpClients.createDefault(); // Create client
             CloseableHttpResponse response = client.execute(request)) { // Execute request

            HttpEntity entity = response.getEntity(); // Get response
            String fullJson = EntityUtils.toString(entity); // Convert to JSON string

            Gson gson = new Gson();

            System.out.println(response.getStatusLine().getStatusCode()); // Get status code
            System.out.println(fullJson);
            JsonObject accountJson = JSONUtils.getAsJsonObject(fullJson);
            JsonArray accountText = accountJson.get("accounts").getAsJsonArray();

            Type type = new TypeToken<ArrayList<MonzoAccount>>(){}.getType(); // Get type

            return new Gson().fromJson(fullJson, type);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
