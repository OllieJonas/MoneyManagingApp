package me.csed2.moneymanager.rest.monzo;

import me.csed2.moneymanager.utils.StateGenerator;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MonzoHttpClient extends me.csed2.moneymanager.rest.HttpClient {

    private String state;

    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private MonzoHttpClient() throws Exception {
        new MonzoServer();
        sendGET();
        Desktop.getDesktop().browse(buildMonzoURI());
    }

    private void sendGET() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(buildMonzoURI())
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = responseFuture.get();
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    private URI buildMonzoURI() throws URISyntaxException {
        StringBuilder builder = new StringBuilder("https://auth.monzo.com/?");
        state = StateGenerator.generate();

        builder.append("client_id=").append(MonzoDetails.CLIENT_ID).append("&");
        builder.append("redirect_uri=").append(MonzoDetails.REDIRECT_URI).append("&");
        builder.append("response_type=code").append("&");
        builder.append("state=").append(state);

        return new URI(builder.toString());
    }
    public static void main(String[] args) {
        try {
            new MonzoHttpClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
