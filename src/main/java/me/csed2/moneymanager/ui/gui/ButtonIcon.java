package me.csed2.moneymanager.ui.gui;

import me.csed2.moneymanager.ui.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class ButtonIcon {

    private BufferedImage icon;

    public ButtonIcon(String address){
        try {
            URL res = getClass().getClassLoader().getResource(address);
            icon = ImageIO.read(Paths.get(res.toURI()).toFile());
        }catch(Exception e){
            System.out.println("image not found: " + address);
        }
    }

    public BufferedImage getIcon(){
        return icon;
    }
}
