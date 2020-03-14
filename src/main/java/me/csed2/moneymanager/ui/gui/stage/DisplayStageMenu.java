package me.csed2.moneymanager.ui.gui.stage;

import com.google.gson.internal.$Gson$Preconditions;
import me.csed2.moneymanager.exceptions.InvalidTypeException;
import me.csed2.moneymanager.main.User;
import me.csed2.moneymanager.ui.Button;
import me.csed2.moneymanager.ui.IAction;
import me.csed2.moneymanager.ui.cmdline.stage.Stage;
import me.csed2.moneymanager.ui.gui.ButtonListener;
import me.csed2.moneymanager.ui.gui.DisplayMenu;
import me.csed2.moneymanager.utils.StringReaderFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DisplayStageMenu extends DisplayMenu {

    protected List<Stage<?>> stages;
    private HashMap<Stage<?>, JTextField> stagesWithTextFields;

    public DisplayStageMenu(int width, int height, String title, DisplayMenu parent){
        this.width = width;
        this.height = height;
        this.title = title;
        this.parent = parent;

        initFrame();
        stages = new ArrayList<Stage<?>>();
        stagesWithTextFields = new HashMap<Stage<?>, JTextField>();
        addStages();
        placeStages();
        addStageButtons();
        frame.pack();
    }


    protected abstract void addStages();

    protected void addStage(Stage<?> stage){
        stages.add(stage);
    }

    private void placeStages(){
        for(Stage<?> stage : stages){
            JPanel stagePanel = new JPanel();

            //Add text to panel
            for(String line : stage.getText()){
                stagePanel.add(new JLabel(line));
            }

            //Add textbox to panel, and to hashmap to tie it to a stage
            JTextField stageTextField = new JTextField(20);
            stagesWithTextFields.put(stage, stageTextField);
            stagePanel.add(stageTextField);

            panel.add(stagePanel);
        }
    }

    private void addStageButtons(){
        JPanel buttonPanel = new JPanel();

        JButton submitButton = new JButton("Submit");
        ButtonListener.getButtonsAndActions().put(title + ":" + submitButton.getActionCommand(), createSubmitAction());
        submitButton.addActionListener(ButtonListener.getInstance());
        buttonPanel.add(submitButton);

        panel.add(buttonPanel);
    }

    private IAction createSubmitAction(){
        return new IAction(){
            @Override
            public void execute(User user){
                try {
                    //Run all Stage Execution Phases
                    for (Stage<?> s : stages) {
                        Object result = StringReaderFactory.parse(stagesWithTextFields.get(s).getText(), s.getResultType());
                        s.setResult(result);
                        s.executionPhase();
                    }

                    //Run this menu's Exit Phase
                    exitPhase();

                }catch(InvalidTypeException e){
                    System.out.println("INVALID TYPE IN TEXT FIELD");
                }
            }
        };
    }

    protected abstract void beginPhase();

    protected abstract void exitPhase();

}
