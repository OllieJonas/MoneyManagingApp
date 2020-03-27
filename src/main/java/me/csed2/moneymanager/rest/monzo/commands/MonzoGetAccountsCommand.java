package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.csed2.moneymanager.main.App;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MonzoGetAccountsCommand implements Function<App, List<MonzoAccount>> {

    @Override
    public List<MonzoAccount> apply(App app) {
        HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/accounts"); // Make request for accounts
        request.addHeader("Authorization", "Bearer " + MonzoHttpClient.getAccessToken()); // Add header showing access token

        try (CloseableHttpClient client = HttpClients.createDefault(); // Create client
             CloseableHttpResponse response = client.execute(request)) { // Execute request

            HttpEntity entity = response.getEntity(); // Get response

            String fullJson = EntityUtils.toString(entity); // Convert to JSON string
            JsonObject initialObject = JSONUtils.getAsJsonObject(fullJson);

            // Handling Monzo's weird JSON formatting...
            JsonArray array = initialObject.getAsJsonArray("accounts");

            List<MonzoAccount> arrList = new ArrayList<>();

            Gson gson = new Gson();

            for (int i = 0; i < array.size(); i++) {
                JsonObject newObject = array.get(i).getAsJsonObject();
                arrList.add(new MonzoAccount(gson.fromJson(newObject, JsonObject.class)));
            }
            MonzoHttpClient.setSelectedAccount(arrList.get(0)); // Set default Selected Account to the first one.

            return arrList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
