package me.csed2.moneymanager.rest.monzo;

import com.sun.net.httpserver.HttpServer;
import me.csed2.moneymanager.rest.RequestServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonzoServer extends RequestServer {

    private HttpServer server;

    private ExecutorService service = Executors.newSingleThreadScheduledExecutor();

    public MonzoServer() {
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
            server.createContext("/oauth/callback", new MonzoHttpHandler());
            server.setExecutor(service);
            server.start();
            System.out.println("Listening for connections...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
