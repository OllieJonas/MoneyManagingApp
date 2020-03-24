package me.csed2.moneymanager.rest.monzo.server;

import lombok.Getter;
import me.csed2.moneymanager.rest.AuthServerHandler;
import me.csed2.moneymanager.rest.monzo.client.MonzoDetails;
import me.csed2.moneymanager.rest.monzo.client.MonzoHttpClient;
import me.csed2.moneymanager.utils.JSONUtils;
import me.csed2.moneymanager.utils.NameValuePairBuilder;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class AuthMonzoHandler extends AuthServerHandler {
    // {"access_token":"eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJlYiI6ImJZZUg3RHI1Q2FQc2o5MTlDa290IiwianRpIjoiYWNjdG9rXzAwMDA5dEV3Wk82N0hIVDlBRGpsZ24iLCJ0eXAiOiJhdCIsInYiOiI2In0.K4h7hwuRdg9V90nRsHjFCKGrVkBhDPQTYJ3lLqsg3GnTabJzrMIju9sxltKjJi-T5sSJL8Q5Gb1Rwk6ok8sB9Q","client_id":"oauth2client_00009tCzlbhKRvzmyNTqAz","expires_in":107999,"scope":"third_party_developer_app.pre_verification","token_type":"Bearer","user_id":"user_00009tCp9hi0eaAopMnkcT"}
    // private static final String EXAMPLE_REQUEST = "/oauth/callback?code=eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJlYiI6IlBxc0FuSkFteHJKalNxdGd2Znp5IiwianRpIjoiYXV0aHpjb2RlXzAwMDA5dERBYllWUVlzM0JRbTJRWmwiLCJ0eXAiOiJhemMiLCJ2IjoiNiJ9.J-K1eCmDJHUIC8AXTK1UeaQf2ZumR0HDrTacvGeyeqwdjr4iPCUR58G_BmvZgxbHSqL09iDWYkh7VYO-DBdDhQ&state=2gjqksd3deq1pvj4fph6b2ctkp";
    // private String test = "http://localhost:8080/oauth/callback?code%3DeyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJlYiI6IlhqcC9keVlNL29aVVVCckJuS29xIiwianRpIjoiYXV0aHpjb2RlXzAwMDA5dEU0THllWkFuUmxub29uRWYiLCJ0eXAiOiJhemMiLCJ2IjoiNiJ9.rfLjcPAALjCgaNyhJHoKdL7ZqIY83CfoMeTlp0MhV8bhyUi5o8tMuduPXlaa5W-G1yFUOVSpf5MBt0bFGdnuhQ%26state%3D8ggvalf1e8b5m1r1qgugq7u320&source=gmail&ust=1584864778277000&usg=AFQjCNFvBJaYIbL_p9hCw1hIU16lbG_rZQ";

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

    private void getAccessToken() throws IOException {
        HttpPost post = new HttpPost("https://api.monzo.com/oauth2/token");

        post.setEntity(new UrlEncodedFormEntity(buildAuthenticationRequest()));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            
            String repString = EntityUtils.toString(response.getEntity());
            String accessToken = JSONUtils.getAsJsonObject(repString).get("access_token").getAsString();

            MonzoHttpClient.setAccessToken(accessToken);
        }
    }

    public List<NameValuePair> buildAuthenticationRequest() {
        return new NameValuePairBuilder()

                .addBasicPair("grant_type", "authorization_code")
                .addBasicPair("client_id", MonzoDetails.CLIENT_ID)
                .addBasicPair("client_secret", MonzoDetails.CLIENT_SECRET)
                .addBasicPair("redirect_uri", MonzoDetails.REDIRECT_URI)
                .addBasicPair("code", authenticationCode)

                .build();
    }
}
