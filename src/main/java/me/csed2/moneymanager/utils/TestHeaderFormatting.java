package me.csed2.moneymanager.utils;

import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.util.CharArrayBuffer;

import java.util.Arrays;

public class TestHeaderFormatting {

    public static void main(String[] args) {
        HttpGet request = new HttpGet(MonzoDetails.MONZO_API + "/transactions");
        request.addHeader("Authorization", "Bearer " + "abc");
        HeaderElement element = new BasicHeaderElement("account_id", "123");
        BasicHeaderValueFormatter formatter = new BasicHeaderValueFormatter();
        Header header = new BasicHeader(element.getName(), element.getValue());
        request.addHeader(element.getName(), element.getValue());

        

        System.out.println(Arrays.toString(request.getAllHeaders()));
    }
}
