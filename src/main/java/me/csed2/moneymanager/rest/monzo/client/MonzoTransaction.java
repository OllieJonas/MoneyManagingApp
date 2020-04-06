package me.csed2.moneymanager.rest.monzo.client;

import com.google.gson.JsonObject;
import me.csed2.moneymanager.rest.JsonWrapped;
import me.csed2.moneymanager.transactions.Transaction;

import java.sql.Date;
import java.time.Instant;

public class MonzoTransaction extends Transaction implements JsonWrapped {

    private JsonObject object;

    public MonzoTransaction(JsonObject object, int id) {
        super(object.get("description").getAsString(),
                id,
                Date.from(Instant.parse(object.get("created").getAsString())),
                object.get("amount").getAsInt(), object.get("category").getAsString(),
                object.get("notes").getAsString().split("\n"),
                object.get("merchant").toString());
        this.object = object;
    }

    @Override
    public JsonObject getWrappedObject() {
        return object;
    }
}
