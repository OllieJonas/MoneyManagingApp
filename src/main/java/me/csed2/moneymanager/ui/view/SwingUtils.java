package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;
import me.csed2.moneymanager.utils.StringParserFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
public class SwingUtils {

    public static JPanel generateMenuPanel(int width, int height, String title){
        JFrame frame = new JFrame(title);

        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit program on close
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); //Center on screen
        frame.setVisible(false);

        //Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        //Border
        Border blackBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black);
        panel.setBorder(blackBorder);

        //Title
        JLabel labTitle = new JLabel(title);
        labTitle.setAlignmentX(Component.CENTER_ALIGNMENT); //Place title in the middle
        labTitle.setFont(new Font("TimesRoman", Font.BOLD, 26));
        panel.add(labTitle);


        return panel;
    }

    public static Consumer<App> createSubmitAction(StageMenu stageNode, HashMap<Stage<?>, JTextField> stageTextboxes){
        return user -> {
            try {
                //Run all Stage Execution Phases
                for (Stage<?> s : stageNode.getStages()) {
                    String input = stageTextboxes.get(s).getText();
                    Object result = StringParserFactory.parse(stageTextboxes.get(s).getText(), s.getResultType());

                    s.setResult(result);
                    s.executionPhase();
                }
                //Run this menu's Exit Phase
                stageNode.exitPhase();

            } catch (InvalidTypeException e) {

            }
        };
    }


    public static ImageIcon getIconFromAddress(String address){
        BufferedImage icon;
        try{
            URL res = SwingUtils.class.getClassLoader().getResource(address);
            icon = ImageIO.read(Paths.get(res.toURI()).toFile());
        }catch(Exception e){
            return null;
        }
        return new ImageIcon(icon);
    }
}
