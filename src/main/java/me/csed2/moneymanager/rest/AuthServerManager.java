package me.csed2.moneymanager.rest;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class AuthServerManager {

    @Getter
    private Set<Server> servers;

    @Getter
    private static AuthServerManager instance;

    public AuthServerManager() {
        servers = new HashSet<>();
        instance = this;
    }

    public void addServer(Server server) {
        servers.add(server);
        server.start();
    }

    public synchronized void close(Server server) {
        server.close();
        servers.remove(server);
    }

    public synchronized void closeAll() {
        for (Server server : servers) {
            server.close();
        }
    }
}
