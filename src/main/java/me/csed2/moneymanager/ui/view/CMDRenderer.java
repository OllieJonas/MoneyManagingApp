package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.charts.adapters.Graph;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.*;
import me.csed2.moneymanager.utils.ConsoleUtils;
import me.csed2.moneymanager.utils.StringAlignUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class CMDRenderer implements UIRenderer {

    @Override
    public void render(UINode node) {

        if (node instanceof Action) {
            Action action = (Action) node;
            action.execute(App.getInstance());

        } else if (node instanceof StageMenu) {
            StageMenu menu = (StageMenu) node;
            Stage<?> initialStage = menu.getStages().get(0);

            menu.beginPhase();
            initialStage.executionPhase();
            printStage(initialStage);

        } else {

            StringAlignUtils util = new StringAlignUtils(50, StringAlignUtils.Alignment.CENTRE);
            ConsoleUtils.printBorder(ConsoleUtils.BorderType.TOP, false);
            System.out.println(util.format(node.getName()));
            System.out.print("\n");

            List<UINode> children = node.getChildren();

            int count = 1;
            for (UINode child : children) {
                System.out.println(util.format(count + ": " + child.getName()));
                count++;
            }
            ConsoleUtils.printBorder(ConsoleUtils.BorderType.BOTTOM);
        }
    }

    @Override
    public void renderText(String message) {
        System.out.println(message);
    }

    @Override
    public void renderStage(Stage<?> stage) {
        printStage(stage);
    }

    @Override
    public void renderGraph(Graph graph) {
        BufferedImage createdImage = graph.getChart().createBufferedImage(600, 800);
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            ImageIO.write(createdImage, "png", bas);
            byte[] byteArray = bas.toByteArray();
            InputStream in = new ByteArrayInputStream(byteArray);
            BufferedImage image = ImageIO.read(in);
            String path = App.getInstance().getSettings().getValue("directory", String.class)
                    .orElse(App.DEFAULT_DIRECTORY);

            File outputFile = new File(path + "/graphs/" + graph.getChart().getTitle().toString());
            ImageIO.write(image, "png", outputFile);
            System.out.println("Success! File has been saved ");
        } catch (IOException e) {
            System.out.println("Error: Unable to create graph! Please contact a developer!");
        }

    }

    private void printStage(Stage<?> stage) {
        for (String line : stage.getText()) {
            System.out.println(line);
        }
    }
}
