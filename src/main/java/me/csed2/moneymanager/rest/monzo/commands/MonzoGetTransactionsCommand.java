package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.rest.monzo.client.pojos.MonzoTransaction;
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

public class MonzoGetTransactionsCommand implements Command<List<MonzoTransaction>> {

    @Override
    public List<MonzoTransaction> execute(App app) {
        HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/transactions?expand[]=merchant&account_id="
                + app.getMonzoClient().getSelectedAccount().getId());
        request.addHeader("Authorization", "Bearer " + MonzoHttpClient.getAccessToken());

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {

            HttpEntity entity = response.getEntity();

            String fullJson = EntityUtils.toString(entity); // Convert to JSON string
            JsonObject initialObject = JSONUtils.getAsJsonObject(fullJson); // Convert String into JSON Object

            // Handling Monzo's weird JSON formatting...
            Gson gson = new Gson();
            JsonArray array = initialObject.getAsJsonArray("transactions"); // Get array transactions
            List<MonzoTransaction> arrList = new ArrayList<>();

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < array.size(); i++) { // Add all objects inside array to a list
                JsonObject newObject = array.get(i).getAsJsonObject(); // Convert each transaction into JsonObject
                MonzoTransaction transaction = new MonzoTransaction(gson.fromJson(newObject, JsonObject.class), i+1);
                builder.append(transaction.toFormattedString()).append("\n");
                arrList.add(transaction); // Convert JsonObject into wrapped MonzoTransaction
            }

            app.render(builder.toString());

            return arrList;

            } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
