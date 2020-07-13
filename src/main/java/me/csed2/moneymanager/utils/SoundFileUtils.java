package me.csed2.moneymanager.utils;

import java.io.File;

public class SoundFileUtils {

    public static File getFileFromString(String s) {
        if (s != null) {
            return new File(SoundFileUtils.class.getClassLoader().getResource(s).getFile());
        }else{
            return null;
        }
    }
}
