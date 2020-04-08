package me.csed2.moneymanager.ui.view;

import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.App;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.gui.ButtonListener;
import me.csed2.moneymanager.ui.model.Stage;
import me.csed2.moneymanager.ui.model.StageMenu;
import me.csed2.moneymanager.ui.model.UINode;
import me.csed2.moneymanager.ui.model.Action;
import me.csed2.moneymanager.utils.StringParserFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.Consumer;

public class SwingRenderer implements UIRenderer {

    private HashMap<UINode, JFrame> nodeFrames = new HashMap<>();
    private HashMap<Stage<?>, JTextField> stageTextboxes = new HashMap<>();
    private JFrame prevFrame;

    @Override
    public void render(UINode node) {
        if(node instanceof Action) {
            ((Action) node).execute(App.getInstance());
        }else {
            renderMenu(node);
        }
    }

    private void renderMenu(UINode node){
        if(prevFrame != null) prevFrame.setVisible(false);

        if(nodeFrames.get(node) == null){
            if(node instanceof StageMenu){
                nodeFrames.put(node, generateStageMenu(node));
            }else{
                nodeFrames.put(node, generateButtonMenu(node));
            }
        }
        nodeFrames.get(node).setVisible(true);
        prevFrame = nodeFrames.get(node);
    }

    private JFrame generateStageMenu(UINode node){
        JPanel panel = SwingUtils.generateMenuPanel(300, 300, node.getName());
        StageMenu stageMenu = (StageMenu) node;

        //Stages
        for(Stage<?> stage : stageMenu.getStages()) {
            JPanel stagePanel = new JPanel();

            //Add text to panel
            for (String line : stage.getText()) {
                stagePanel.add(new JLabel(line));
            }

            //Add textbox to panel, and to hashmap to tie it to a stage
            JTextField stageTextField = new JTextField(20);
            stageTextboxes.put(stage, stageTextField);
            stagePanel.add(stageTextField);

            panel.add(stagePanel);
        }

        addStageButtonPanel(node, panel);

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        frame.pack();
        return frame;
    }

    private void addStageButtonPanel(UINode node, JPanel panel){
        //Extra Buttons
        JPanel buttonPanel = new JPanel();

        JButton submitButton = new JButton("Submit");
        ButtonListener.getButtonsAndActions().put(node.getName() + ":" + submitButton.getActionCommand(), SwingUtils.createSubmitAction((StageMenu) node, stageTextboxes));
        submitButton.addActionListener(ButtonListener.getInstance());

        JButton backButton = new JButton("Back");
        ButtonListener.getButtonsAndActions().put(node.getName() + ":" + backButton.getActionCommand(), (Consumer<App>) app -> app.render(node.getParent()));
        backButton.addActionListener(ButtonListener.getInstance());

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        panel.add(buttonPanel);
    }

    private JFrame generateButtonMenu(UINode node){
        JPanel panel = SwingUtils.generateMenuPanel(300, 300, node.getName());

        //Init Pair Panels
        Stack<JPanel> pairPanels = new Stack<JPanel>();
        pairPanels.push(new JPanel(new FlowLayout()));

        //Button
        for(UINode child : node.getChildren()){
            //Configure Button
            JButton button;

            if(SwingUtils.getIconFromAddress(child.getImage()) == null){
                button = new JButton(child.getName());
                button.setPreferredSize(new Dimension(80, 80));
            }else{
                button = new JButton(SwingUtils.getIconFromAddress(child.getImage()));
                button.setActionCommand(child.getName());
                button.setBorder(BorderFactory.createEmptyBorder()); //Delete border
                button.setContentAreaFilled(false); //Remove contents
            }


            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(ButtonListener.getInstance());
            ButtonListener.getButtonsAndActions().put(node.getName() + ":" + child.getName(), app -> app.render(child));

            addToPairPanels(pairPanels, button);
        }

        //Place Pair Panels
        for(JPanel p : pairPanels){
            panel.add(p);
            panel.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel);
        frame.pack();
        return frame;
    }

    private void addToPairPanels(Stack<JPanel> pairPanels, JButton button){

        //Create middle gap
        if(pairPanels.peek().getComponents().length == 1){
            pairPanels.peek().add(Box.createRigidArea(new Dimension(40, 0)));
        }

        if(pairPanels.peek().getComponents().length < 3){
            pairPanels.peek().add(button);
        }else{
            pairPanels.add(new JPanel(new FlowLayout()));
            addToPairPanels(pairPanels, button);
        }
    }


    @Override
    public void sendMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    public void renderStage(Stage<?> stage) {

    }
}
