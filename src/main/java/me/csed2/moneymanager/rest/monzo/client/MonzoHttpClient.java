package me.csed2.moneymanager.rest.monzo.client;

import me.csed2.moneymanager.rest.BankClient;
import me.csed2.moneymanager.rest.monzo.client.pojos.MonzoAccount;
import me.csed2.moneymanager.utils.StateGenerator;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MonzoHttpClient extends BankClient<MonzoAccount> {

    private String state;

    public MonzoHttpClient() {

    }

    @Override
    public void accessPage() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(buildURI());
        }
    }

    @Override
    protected URI buildURI() throws URISyntaxException {
        StringBuilder builder = new StringBuilder("https://auth.monzo.com/?");
        state = StateGenerator.generate();

        builder.append("client_id=").append(MonzoDetails.CLIENT_ID.VALUE).append("&");
        builder.append("redirect_uri=").append(MonzoDetails.REDIRECT_URI.VALUE).append("&");
        builder.append("response_type=").append("code").append("&");
        builder.append("state=").append(state);

        return new URI(builder.toString());
    }
}
