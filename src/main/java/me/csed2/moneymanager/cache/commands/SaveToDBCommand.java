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
import java.util.function.BiFunction;
import java.util.function.Function;

public class SaveToDBCommand<T extends Cacheable> implements BiFunction<String, List<T>, Boolean> {

    @Override
    public Boolean apply(String fileName, List<T> items) {
        String jsonString = new Gson().toJson(items);

        URL url = Main.class.getClassLoader().getResource(fileName);

        try {
            assert url != null;
            System.out.println(url.getPath());
            Path path = Paths.get(url.toURI());
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
