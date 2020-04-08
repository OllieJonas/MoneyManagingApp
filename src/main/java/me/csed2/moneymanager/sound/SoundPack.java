package me.csed2.moneymanager.sound;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import me.csed2.moneymanager.main.Main;

import javax.sound.sampled.Clip;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;

public class SoundPack {

    private HashMap<String, NodeSounds> nodeSounds = new HashMap<String, NodeSounds>();

    private Gson gson;

    private Type type;

    private JsonReader reader;

    public SoundPack(String name) {
        try {

            this.gson = new Gson();
            this.type = new TypeToken<HashMap<String, NodeSounds>>() {
            }.getType();

            URL url = Main.class.getClassLoader().getResource("sounds.json");

            if (url != null) {
                this.reader = new JsonReader(new FileReader(url.getPath()));
            }

            nodeSounds = gson.fromJson(reader, type);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public Clip getLoadClip(String nodeName){
        if(nodeSounds.get(nodeName) == null) return null;
        return nodeSounds.get(nodeName).getLoadClip();
    }

    public Clip getSubmitClip(String nodeName){
        if(nodeSounds.get(nodeName) == null) return null;
        return nodeSounds.get(nodeName).getSubmitClip();
    }
}
