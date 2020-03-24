package me.csed2.moneymanager.rest.monzo.commands;

import com.google.gson.reflect.TypeToken;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.rest.monzo.client.MonzoAccount;
import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import org.apache.http.client.methods.HttpGet;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MonzoListAccountsCommand implements ICommand<List<MonzoAccount>> {

    @Override
    public List<MonzoAccount> execute() {
        HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/balance");
        Type type = new TypeToken<ArrayList<MonzoAccount>>(){}.getType();
        return null;
    }
}
