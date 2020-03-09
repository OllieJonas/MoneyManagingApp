package me.csed2.moneymanager.ui.cmdline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader implements Runnable {

    private BufferedReader reader;

    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            while (reader.ready()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
            }

            String input = reader.readLine();

            new InputProcessor(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
