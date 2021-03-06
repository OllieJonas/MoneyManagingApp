package me.csed2.moneymanager.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.HashMap;
import java.util.function.Consumer;

public abstract class AuthServerHandler implements HttpHandler {

    private HashMap<String, Consumer<String>> responses;

    public AuthServerHandler() {
        this.responses = new HashMap<>();
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

    public abstract AuthServerHandler run();

    protected void addResponse(String name, Consumer<String> response) {
        responses.put(name, response);
    }
}
