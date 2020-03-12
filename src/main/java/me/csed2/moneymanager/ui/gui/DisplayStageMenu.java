package me.csed2.moneymanager.ui.gui;

public abstract class DisplayStageMenu extends DisplayMenu {

    public DisplayStageMenu(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;

        initFrame();
        addStages();
        placeStages();
    }


    protected abstract void addStages();

    protected void addStage(){

    }

    private void placeStages(){

    }

    public void print(){
        frame.setVisible(true);
    }
}
