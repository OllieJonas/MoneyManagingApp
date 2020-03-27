package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import me.csed2.moneymanager.cache.Cacheable;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.main.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public class SaveToDBCommand<T extends Cacheable> implements Function<App, Boolean> {

    private final Gson gson;
    private final URL fileUrl;

    private final List<T> items;

    public SaveToDBCommand(String fileName, List<T> items) {
        this.gson = new Gson();
        this.fileUrl = Main.class.getClassLoader().getResource(fileName);
        this.items = items;
    }

    @Override
    public Boolean apply(App app) {
        String jsonString = gson.toJson(items);

        try {
            System.out.println(fileUrl.getPath());
            Path path = Paths.get(fileUrl.toURI());
            File myFile = path.toFile();

            if (myFile.delete()) {
                if (myFile.createNewFile()) {
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

                    myOutWriter.append(jsonString);

                    myOutWriter.close();
                    fOut.close();
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
