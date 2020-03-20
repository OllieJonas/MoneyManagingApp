package me.csed2.moneymanager.ui.cmdline;

import me.csed2.moneymanager.main.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class listens for any input typed into the console, then sends it through the InputProcessor.
 *
 * @author Ollie
 * @since 08/03/2020
 */
public class InputReader extends Thread {

    /**
     * Reader for user-in
     */
    private BufferedReader reader;

    /**
     * Default constructor for InputReader. Initialises BufferedReader field.
     */
    public InputReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * What runs upon calling this thread. Listens for input.
     */
    @Override
    public void run() {
        while (!interrupted()) {
            try {
                while (!reader.ready()) { // Allow the thread to be interrupted.
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

    /**
     * Closes the input reader.
     */
    private synchronized void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
