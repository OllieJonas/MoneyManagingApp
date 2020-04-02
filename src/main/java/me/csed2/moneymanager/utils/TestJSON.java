package me.csed2.moneymanager.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.csed2.moneymanager.cache.commands.SaveListToDBCommand;
import me.csed2.moneymanager.command.CommandDispatcher;
import me.csed2.moneymanager.rest.monzo.client.MonzoTransaction;

import java.util.ArrayList;
import java.util.List;

public class TestJSON {
    
    private String transJson = "{\"transactions\":[{\"id\":\"tx_00009tbjNhPunEYeq2Akb4\",\"created\":\"2020-04-01T18:10:53.493Z\",\"description\":\"HSBC\",\"amount\":2000,\"fees\":{},\"currency\":\"GBP\",\"merchant\":null,\"notes\":\"HSBC\",\"metadata\":{\"faster_payment\":\"true\",\"fps_fpid\":\"3698933501911040011020200401826401505\",\"fps_payment_id\":\"36989335019110400120200401826401505\",\"insertion\":\"entryset_00009tbjNhC5ce0KkoRWKZ\",\"notes\":\"HSBC\",\"trn\":\"369893350191104001\"},\"labels\":null,\"account_balance\":0,\"attachments\":[],\"international\":null,\"category\":\"general\",\"categories\":null,\"is_load\":false,\"settled\":\"2020-04-02T06:00:00Z\",\"local_amount\":2000,\"local_currency\":\"GBP\",\"updated\":\"2020-04-01T18:10:53.577Z\",\"account_id\":\"acc_00009tCqeFZDQyVERoioio\",\"user_id\":\"\",\"counterparty\":{\"account_number\":\"12365383\",\"name\":\"JONAS O N\",\"sort_code\":\"401505\",\"user_id\":\"anonuser_db76d2ccafc6c768899f6c\"},\"scheme\":\"payport_faster_payments\",\"dedupe_id\":\"com.monzo.fps:9200:3698933501911040011020200401826401505:INBOUND\",\"originator\":false,\"include_in_spending\":false,\"can_be_excluded_from_breakdown\":false,\"can_be_made_subscription\":false,\"can_split_the_bill\":false,\"can_add_to_tab\":false,\"amount_is_pending\":false}]}";
    private TestJSON() {
        testTransactionJson(transJson);
    }

    private void testTransactionJson(String fullJson) {
        JsonObject initialObject = JSONUtils.getAsJsonObject(fullJson); // Convert String into JSON Object

        // Handling Monzo's weird JSON formatting...
        Gson gson = new GsonBuilder().serializeNulls().create();
        JsonArray array = initialObject.getAsJsonArray("transactions"); // Get array transactions
        System.out.println(array);
        List<MonzoTransaction> arrList = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) { // Add all objects inside array to a list
            JsonObject newObject = array.get(i).getAsJsonObject(); // Convert each transaction into JsonObject
            arrList.add(new MonzoTransaction(gson.fromJson(newObject, JsonObject.class), i+1)); // Convert JsonObject into wrapped MonzoTransaction
        }

        CommandDispatcher.dispatchSync(new SaveListToDBCommand<>("monzo_transactions.json", arrList));
        System.out.println(arrList.get(0).getVendor());
    }

    public static void main(String[] args) {
        new TestJSON();
    }
}
