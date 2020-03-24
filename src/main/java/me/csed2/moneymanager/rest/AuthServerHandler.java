package me.csed2.moneymanager.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class AuthServerHandler implements HttpHandler {

    private HashMap<String, BiConsumer<HttpExchange, String>> responses;

    private BiConsumer<HttpExchange, String> defaultResponse;

    public AuthServerHandler() {
        responses = new HashMap<>();
        addResponses();
    }

    @Override
    public void handle(HttpExchange exchange) {
        String fullReply = exchange.getRequestURI().toString();
        String reply = fullReply.split("\\?")[1].split("=")[0];

        boolean executed = false;
        for (String tag : responses.keySet()) {
            if (reply.contains(tag)) {
                responses.get(tag).accept(exchange, fullReply);
                executed = true;
                break;
            }
        }
        if (!executed) {
            defaultResponse.accept(exchange, fullReply);
        }
    }

    public abstract void addResponses();

    public abstract List<NameValuePair> buildAuthenticationRequest();

    protected void addResponse(String tag, BiConsumer<HttpExchange, String> response) {
        responses.put(tag, response);
    }

    protected void addDefaultResponse(BiConsumer<HttpExchange, String> response) {
        this.defaultResponse = response;
    }
}
