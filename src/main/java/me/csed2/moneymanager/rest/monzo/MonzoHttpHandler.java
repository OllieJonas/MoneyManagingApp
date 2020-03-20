package me.csed2.moneymanager.rest.monzo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class MonzoHttpHandler implements HttpHandler {

    private static final String EXAMPLE_REQUEST = "/oauth/callback?code=eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJlYiI6IlBxc0FuSkFteHJKalNxdGd2Znp5IiwianRpIjoiYXV0aHpjb2RlXzAwMDA5dERBYllWUVlzM0JRbTJRWmwiLCJ0eXAiOiJhemMiLCJ2IjoiNiJ9.J-K1eCmDJHUIC8AXTK1UeaQf2ZumR0HDrTacvGeyeqwdjr4iPCUR58G_BmvZgxbHSqL09iDWYkh7VYO-DBdDhQ&state=2gjqksd3deq1pvj4fph6b2ctkp";
    private static String authentiationCode;

    private static String state;

    @Getter
    private boolean authenticated = false;


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!authenticated) {
            handleRequest(exchange.getRequestURI().toString());
        }
    }

    public static void main(String[] args) {
        handleRequest(EXAMPLE_REQUEST);
    }

    private static void handleRequest(String response) {
        authentiationCode = response.split("=")[1].split("&")[0];
        state = response.split("&")[1].split("=")[1];
        
        System.out.println(authentiationCode);
        System.out.println(state);
    }

    private void buildConfirmationWebsite() {

    }
}
