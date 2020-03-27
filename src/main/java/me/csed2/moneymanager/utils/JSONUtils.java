package me.csed2.moneymanager.utils;

import com.google.gson.*;

public class JSONUtils {

    public static JsonObject getAsJsonObject(String text) {
        return new Gson().fromJson(text, JsonObject.class);
    }

}
