package me.csed2.moneymanager.rest.monzo.commands;

import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.rest.monzo.client.MonzoTransaction;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpGet;

import java.util.List;
import java.util.function.Supplier;

public class MonzoGetTransactionsCommand implements Supplier<List<MonzoTransaction>> {

    @Override
    public List<MonzoTransaction> get() {
        HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/transactions");
        request.addHeader("Authorization", "Bearer " + MonzoHttpClient.getAccessToken());
        request.addHeader("account_id", MonzoHttpClient.getSelectedAccount().getId());
        return null;
    }
}
