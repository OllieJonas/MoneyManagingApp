package me.csed2.moneymanager.utils;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class SoundFileUtils {

    public File getFileFromString(String s) {
        if (s != null) {
            return new File(SoundFileUtils.class.getClassLoader().getResource(s).getFile());
        }else{
            return null;
        }
    }
}
