package me.csed2.moneymanager.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public abstract class AuthServerHandler implements HttpHandler {

    private HashMap<String, Consumer<String>> responses;

    public AuthServerHandler() {
        responses = new HashMap<>();
        addResponses();
    }

    @Override
    public void handle(HttpExchange exchange) {
        String fullReply = exchange.getRequestURI().toString();
        String reply = fullReply.split("\\?")[1].split("=")[0];

        for (String tag : responses.keySet()) {
            if (reply.contains(tag)) {
                responses.get(tag).accept(fullReply);
                break;
            }
        }
    }

    public abstract void addResponses();

    public abstract List<NameValuePair> buildAuthenticationRequest();

    protected void addResponse(String name, Consumer<String> response) {
        responses.put(name, response);
    }
}
