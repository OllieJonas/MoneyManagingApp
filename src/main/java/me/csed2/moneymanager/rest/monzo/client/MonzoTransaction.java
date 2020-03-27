package me.csed2.moneymanager.rest.monzo.client;

import com.google.gson.JsonObject;
import me.csed2.moneymanager.rest.JsonWrapped;

public class MonzoTransaction implements JsonWrapped {

    private JsonObject object;

    public MonzoTransaction(JsonObject object) {
        this.object = object;
    }

    @Override
    public JsonObject getWrappedObject() {
        return object;
    }
}
