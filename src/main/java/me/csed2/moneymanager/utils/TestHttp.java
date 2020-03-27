package me.csed2.moneymanager.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHttp {

    private Map<Object, Object> postMap = new HashMap<>();

    private TestHttp() {
        postMap.put("foo", "This is expected to be sent back as part of the response body.");

        try {
            testPost();
            testGet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testPost() throws IOException {
        HttpPost post = new HttpPost("https://postman-echo.com/post");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("test", "test hello"));

        post.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }

    private void testGet() throws IOException {
        HttpGet request = new HttpGet("https://postman-echo.com/get?hello=hi");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        }
    }

    public static void main(String[] args) {
        new TestHttp();
    }
}
