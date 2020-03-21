package me.csed2.moneymanager.rest.monzo.server;

import com.sun.net.httpserver.HttpServer;
import me.csed2.moneymanager.rest.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuthMonzoServer implements Server {

    private HttpServer server;

    private ExecutorService service = Executors.newSingleThreadScheduledExecutor();

    private int port;

    public AuthMonzoServer(int port) throws IOException {
        this.port = port;

        server = HttpServer.create(new InetSocketAddress("localhost", port), 0);

        server.createContext("/oauth/callback", new AuthMonzoHandler());

        server.setExecutor(service);
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public HttpServer getServer() {
        return server;
    }

    @Override
    public void close() {
        service.shutdownNow();
        service.shutdown();
    }

    @Override
    public void start() {
        server.start();
        System.out.println("Listening for connections...");
    }
}
