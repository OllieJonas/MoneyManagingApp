package me.csed2.moneymanager.utils;

import me.csed2.moneymanager.main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Notifications {

    private static TrayIcon trayIcon;

    static {
        try {
            if (SystemTray.isSupported()) {
                SystemTray tray = SystemTray.getSystemTray();
                PopupMenu popupMenu = new PopupMenu();

                URL url = Main.class.getClassLoader().getResource("images/leon.jpeg");

                Image image = Toolkit.getDefaultToolkit().getImage(url);
                trayIcon = new TrayIcon(image, "CSED", popupMenu);
                tray.add(trayIcon);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void displayNotification(String message, TrayIcon.MessageType type) {
        trayIcon.displayMessage("CSED", message, type);
    }

}
