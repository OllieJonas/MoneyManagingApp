package me.csed2.moneymanager.utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class NameValuePairBuilder {

    private List<NameValuePair> values;

    public NameValuePairBuilder() {
        this.values = new ArrayList<>();
    }

    public NameValuePairBuilder addBasicPair(String name, String value) {
        values.add(new BasicNameValuePair(name, value));
        return this;
    }

    public List<NameValuePair> build() {
        return values;
    }
}
