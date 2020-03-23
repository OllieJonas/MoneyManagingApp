package me.csed2.moneymanager.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONUtils {

    public static JsonObject getAsJsonObject(String text) {
        return new Gson().fromJson(text, JsonObject.class);
    }
}
