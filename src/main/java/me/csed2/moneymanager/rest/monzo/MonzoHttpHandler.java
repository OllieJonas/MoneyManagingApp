package me.csed2.moneymanager.rest.monzo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;
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
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MonzoHttpHandler implements HttpHandler {

    private static final String EXAMPLE_REQUEST = "/oauth/callback?code=eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJlYiI6IlBxc0FuSkFteHJKalNxdGd2Znp5IiwianRpIjoiYXV0aHpjb2RlXzAwMDA5dERBYllWUVlzM0JRbTJRWmwiLCJ0eXAiOiJhemMiLCJ2IjoiNiJ9.J-K1eCmDJHUIC8AXTK1UeaQf2ZumR0HDrTacvGeyeqwdjr4iPCUR58G_BmvZgxbHSqL09iDWYkh7VYO-DBdDhQ&state=2gjqksd3deq1pvj4fph6b2ctkp";
    private String test = "http://localhost:8080/oauth/callback?code%3DeyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJlYiI6IlhqcC9keVlNL29aVVVCckJuS29xIiwianRpIjoiYXV0aHpjb2RlXzAwMDA5dEU0THllWkFuUmxub29uRWYiLCJ0eXAiOiJhemMiLCJ2IjoiNiJ9.rfLjcPAALjCgaNyhJHoKdL7ZqIY83CfoMeTlp0MhV8bhyUi5o8tMuduPXlaa5W-G1yFUOVSpf5MBt0bFGdnuhQ%26state%3D8ggvalf1e8b5m1r1qgugq7u320&source=gmail&ust=1584864778277000&usg=AFQjCNFvBJaYIbL_p9hCw1hIU16lbG_rZQ";
    private String authenticationCode;

    private static String state;

    @Getter
    private boolean authenticated = false;


    public MonzoHttpHandler() {

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println(System.currentTimeMillis());
        if (!authenticated) {
            handleRequest(exchange.getRequestURI().toString());
            try {
                getAccessToken(authenticationCode);
                authentication();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void authentication() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.monzo.com/ping/whoami")).build();

        try {

            HttpResponse<String> response = MonzoHttpClient.client.send(request, HttpResponse.BodyHandlers.ofString());
            String auth = response.body();
            System.out.println(auth);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ;new MonzoHttpHandler();
    }

    private void handleRequest(String response) {
        authenticationCode = response.split("=")[1].split("&")[0];
        state = response.split("&")[1].split("=")[1];
        
        System.out.println(authenticationCode);
        System.out.println(state);
    }

    public void getAccessToken(String authenticationCode) throws InterruptedException, IOException {
        HttpPost post = new HttpPost("https://api.monzo.com/oauth2/token");

        post.setEntity(new UrlEncodedFormEntity(buildAuthenticationRequest(authenticationCode)));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }

    public List<NameValuePair> buildAuthenticationRequest(String authenticationCode) {
        List<NameValuePair> params = new ArrayList<>();

        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("client_id", MonzoDetails.CLIENT_ID));
        params.add(new BasicNameValuePair("client_secret", MonzoDetails.CLIENT_SECRET));
        params.add(new BasicNameValuePair("redirect_uri", MonzoDetails.REDIRECT_URI));
        params.add(new BasicNameValuePair("code", authenticationCode));

        return params;
    }

    private void buildConfirmationWebsite() {

    }
}
