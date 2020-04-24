package me.csed2.moneymanager.rest.monzo.server;

import com.sun.net.httpserver.HttpServer;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.rest.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AuthMonzoServer implements Server {

    private HttpServer server; // The server object.

    private ExecutorService service = Executors.newSingleThreadScheduledExecutor(); // Like implements Runnable, but allows one to assign the HttpServer to this thread.

    private int port;

    public AuthMonzoServer(int port) throws IOException {
        this.port = port;

        server = HttpServer.create(new InetSocketAddress("localhost", port), 0); // Start a localhost server on port to listen to responses from Monzo

        server.createContext("/oauth/callback", new AuthMonzoHandler().run()); // Set the listener to this path.

        server.setExecutor(service); // Set asynchronous
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
        service.shutdownNow(); // Force close thread
        service.shutdown(); // Ensure everything has closed properly
    }

    @Override
    public void start() {
        server.start(); // Start the server
        App.getInstance().render("Please type in your email address on the Monzo webpage");
    }
}
