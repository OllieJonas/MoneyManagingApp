package me.csed2.moneymanager.rest;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public abstract class BankClient<T extends Account> {

    @Getter
    private List<T> accounts = new ArrayList<>();

    @Setter @Getter
    private String accessToken;

    public abstract void accessPage() throws URISyntaxException, IOException;

    protected abstract URI buildURI() throws URISyntaxException;

    public void addAccount(T account) {
        accounts.add(account);
    }

    public void removeAccount(T account) {
        accounts.remove(account);
    }
}
