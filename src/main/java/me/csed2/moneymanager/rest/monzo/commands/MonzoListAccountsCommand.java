package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.rest.monzo.client.MonzoAccount;
import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
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

public class MonzoListAccountsCommand implements ICommand<List<MonzoAccount>> {

    @Override
    public List<MonzoAccount> execute() {
        HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/accounts"); // Make request for accounts
        request.addHeader("Authorization", "Bearer " + MonzoHttpClient.getAccessToken()); // Add header showing access token

        try (CloseableHttpClient client = HttpClients.createDefault(); // Create client
             CloseableHttpResponse response = client.execute(request)) { // Execute request

            HttpEntity entity = response.getEntity(); // Get response
            String json = EntityUtils.toString(entity); // Convert to JSON string

            System.out.println(response.getStatusLine().getStatusCode()); // Get status code
            System.out.println(json);

            Type type = new TypeToken<ArrayList<MonzoAccount>>(){}.getType(); // Get type

            return new Gson().fromJson(json, type);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
