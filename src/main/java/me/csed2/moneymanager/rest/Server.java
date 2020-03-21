package me.csed2.moneymanager.rest;

import com.sun.net.httpserver.HttpServer;

/**
 *
 *
 * MONZO: 8080
 */
public interface Server {

    int getPort();

    HttpServer getServer();

    void close();

    void start();
}
