package me.csed2.moneymanager.sound;

import lombok.Getter;
import me.csed2.moneymanager.main.Main;

import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;

@Getter
public class SoundPack {

    private File dir;
    private String dirName;

    private Map<Sound, Clip> clipMap;

    public SoundPack(String dirName) {
        this.dirName = dirName;
        this.dir = getDir(dirName).orElse(null);
        this.clipMap = buildMap();
    }

    private Map<Sound, Clip> buildMap() {
        return Arrays.asList(Sound.getValues()).parallelStream().collect(Collector.of(HashMap::new, (map, item) -> map.put(item, AudioDispatcher.loadClip( "audio/" + dirName + "/" + item.getFile())), (left, right) -> {left.putAll(right); return left;}, Collector.Characteristics.UNORDERED));
    }

    private Optional<File> getDir(String name) {
        return Optional.of(new File(SoundPack.class.getClassLoader().getResource("audio/" + name).getFile()));
    }

    Clip getClip(Sound sound) {
        return clipMap.get(sound);
    }


}
