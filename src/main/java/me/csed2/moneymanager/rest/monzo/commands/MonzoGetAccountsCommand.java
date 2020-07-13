package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.rest.monzo.client.pojos.MonzoAccount;
import me.csed2.moneymanager.utils.JSONUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class MonzoGetAccountsCommand implements Function<App, List<MonzoAccount>> {

    private HttpEntity entity;

    @Override
    public List<MonzoAccount> apply(App app) {

        String accessToken = MonzoHttpClient.getAccessToken();

        if (accessToken != null) {
            HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/accounts"); // Make request for accounts
            request.addHeader("Authorization", "Bearer " + MonzoHttpClient.getAccessToken()); // Add header showing access token

            try (CloseableHttpClient client = HttpClients.createDefault(); // Create client
                 CloseableHttpResponse response = client.execute(request)) { // Execute request

                if (response.getStatusLine().getStatusCode() == 403) { // User has the access token but isn't authorized (ie. they haven't authorized in Monzo app.
                    app.render("Error: Please ensure to authorize our app in your Monzo app!");
                    return null;
                } else {

                    HttpEntity entity = response.getEntity();

                    String fullJson = EntityUtils.toString(entity); // Convert to JSON string
                    JsonObject initialObject = JSONUtils.getAsJsonObject(fullJson);

                    // Handling Monzo's weird JSON formatting...
                    JsonArray array = initialObject.getAsJsonArray("accounts");

                    Gson gson = new Gson();

                    StringBuilder builder = new StringBuilder();

                    for (int i = 0; i < array.size(); i++) {
                        JsonObject newObject = array.get(i).getAsJsonObject();
                        MonzoAccount account = new MonzoAccount(gson.fromJson(newObject, JsonObject.class));
                        builder.append(account.toFormattedString()).append("\n");
                        app.getMonzoClient().addAccount(account);
                    }

                    app.render(builder.toString());

                    app.getMonzoClient().setSelectedAccount(app.getMonzoClient().getAccounts().get(0)); // Set default Selected Account to the first one.

                    return app.getMonzoClient().getAccounts();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            app.render("Error: Please ensure to authenticate with Monzo first!");
        }
        return null;
    }
}
