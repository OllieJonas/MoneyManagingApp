package me.csed2.moneymanager.rest.monzo.client;

import lombok.Setter;
import me.csed2.moneymanager.rest.monzo.server.AuthMonzoServer;
import me.csed2.moneymanager.utils.StateGenerator;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MonzoHttpClient {

    private String state;

    @Setter
    private static String accessToken;

    public static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public MonzoHttpClient() {

    }

    public void accessPage() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(buildMonzoURI());
        }
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
}
