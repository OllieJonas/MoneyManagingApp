package me.csed2.moneymanager.ui.gui;

import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.ui.Button;

public class DisplayExample extends DisplayButtonMenu {

    public DisplayExample(){
        super(300, 300, "Example");
    }


    protected void addButtons(){
        addButton(new Button("I do nothing", user -> System.out.println("Sike"), true, true));
        addButton(new Button("Me neither", user -> CategoryRepository.getInstance().print(), true, true));
    }
}
