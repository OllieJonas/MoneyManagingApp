package me.csed2.moneymanager.rest.monzo.server;

import lombok.Getter;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.AuthServerHandler;
import me.csed2.moneymanager.rest.BankClient;
import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.utils.JSONUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthMonzoHandler extends AuthServerHandler {

    private String authenticationCode;

    private static String state;

    @Getter
    private boolean authenticated = false;

    @Override
    public void addResponses() {
        // Listens for authentication
        addResponse("code", reply -> {
            authenticationCode = reply.split("=")[1].split("&")[0]; // Gets the authentication code from the URL callback from Monzo
            state = reply.split("&")[1].split("=")[1]; // Gets the state

            try {
                getAccessToken(); // Use this newly assigned authentication code to get the Access Token from Monzo
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public AuthServerHandler run() {
        addResponses();
        return this;
    }

    private void getAccessToken() throws IOException {
        HttpPost post = new HttpPost("https://api.monzo.com/oauth2/token");

        List<NameValuePair> authRequest = buildAuthenticationRequest();

        post.setEntity(new UrlEncodedFormEntity(authRequest));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            String repString = EntityUtils.toString(response.getEntity());
            String accessToken = JSONUtils.getAsJsonObject(repString).get("access_token").getAsString();

            BankClient.setAccessToken(accessToken);
            App.getInstance().render("Please check your Monzo app to complete authentication!");
        }
    }

    public List<NameValuePair> buildAuthenticationRequest() {
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
        pairs.add(new BasicNameValuePair("client_id", MonzoDetails.CLIENT_ID));
        pairs.add(new BasicNameValuePair("client_secret", MonzoDetails.CLIENT_SECRET));
        pairs.add(new BasicNameValuePair("redirect_uri", MonzoDetails.REDIRECT_URI));
        pairs.add(new BasicNameValuePair("code", authenticationCode));

        return pairs;
    }
}
