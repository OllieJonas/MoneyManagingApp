package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.main.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader extends Thread {

    private BufferedReader reader;

    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (!interrupted()) {
            try {
                while (reader.ready()) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        break;
                    }
                }

                String input = reader.readLine();

                InputProcessor.process(User.getInstance(), input);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        close();
    }

    public synchronized void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
