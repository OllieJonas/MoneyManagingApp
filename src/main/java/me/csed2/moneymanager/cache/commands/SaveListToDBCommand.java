package me.csed2.moneymanager.cache.commands;

import com.google.gson.Gson;
import me.csed2.moneymanager.command.Command;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.main.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class SaveListToDBCommand<E> implements Command<Boolean> {

    private String name;
    private List<E> items;

    public SaveListToDBCommand(String name, List<E> items) {
        this.name = name;
        this.items = items;
    }

    @Override
    public Boolean execute(App app) {
        String jsonString = new Gson().toJson(items);

        URL url = Main.class.getClassLoader().getResource(name);

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
                System.out.println("hi");
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
