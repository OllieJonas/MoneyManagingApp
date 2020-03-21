package me.csed2.moneymanager.rest;

import me.csed2.moneymanager.utils.HTTPUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHttp {

    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private String postString = "test=hello";

    private Map<Object, Object> postMap = new HashMap<>();

    private TestHttp() {
        postMap.put("foo", "This is expected to be sent back as part of the response body.");

        try {
            testPost();
            testGet();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testPost() throws IOException, InterruptedException {
        HttpPost post = new HttpPost("https://postman-echo.com/post");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("test", "test hello"));

        post.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }

    private void testGet() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://postman-echo.com/get?hello=hi"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
    public static void main(String[] args) {
        new TestHttp();
    }
}
