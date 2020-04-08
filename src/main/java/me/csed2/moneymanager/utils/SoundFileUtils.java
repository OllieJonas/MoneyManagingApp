package me.csed2.moneymanager.utils;

import me.csed2.moneymanager.main.App;

import java.io.*;
import java.net.URL;

public class SoundFileUtils {

    public static File getFileFromString(String s) {
        if (s != null) {
            return new File(SoundFileUtils.class.getClassLoader().getResource(s).getFile());
        }else{
            return null;
        }
    }
}
