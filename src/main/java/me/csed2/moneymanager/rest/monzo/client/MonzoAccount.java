package me.csed2.moneymanager.rest.monzo.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.csed2.moneymanager.rest.Account;

@Getter
@AllArgsConstructor
public class MonzoAccount implements Account {

    private String id;

    private String description;

    private String created;
}
