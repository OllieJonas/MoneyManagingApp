package me.csed2.moneymanager.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestJSON {

    private String jsonString = "{\"accounts\":[{\"id\":\"acc_00009tCqeFZDQyVERoioio\",\"closed\":false,\"created\":\"2020-03-20T18:04:32.171Z\",\"description\":\"user_00009tCp9hi0eaAopMnkcT\",\"type\":\"uk_retail\",\"currency\":\"GBP\",\"country_code\":\"GB\",\"owners\":[{\"user_id\":\"user_00009tCp9hi0eaAopMnkcT\",\"preferred_name\":\"Ollie Jonas\",\"preferred_first_name\":\"Ollie\"}],\"account_number\":\"57544951\",\"sort_code\":\"040004\",\"payment_details\":{\"locale_uk\":{\"account_number\":\"57544951\",\"sort_code\":\"040004\"}}}]}";


    private TestJSON() {

    }

    public static void main(String[] args) {
        TestJSON testJSON = new TestJSON();
        testJSON.test();
    }

    private void test() {
        JsonObject initialObject= JSONUtils.getAsJsonObject(jsonString);
        JsonArray array = initialObject.getAsJsonArray("accounts");
        JsonObject newObject = array.get(0).getAsJsonObject();

        System.out.println(newObject);

        Type listType = new TypeToken<ArrayList<String>>(){}.getType();

        JsonObject list = new Gson().fromJson(newObject, JsonObject.class);
        JsonElement element = list.get("id");
        System.out.println(element);

    }
}
