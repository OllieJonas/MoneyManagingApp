package me.csed2.moneymanager.sound;

import lombok.Getter;

public enum Sound {

    // Greeting
    GREETING("Greeting.wav"),

    //Cache Item
    CATEGORY("Category.wav"),
    SUBSCRIPTION("Subscription.wav"),
    TRANSACTION("Transaction.wav"),

    // Editing Cache Item
    YOU_HAVE_ADDED_STEM("YouHaveAdded.wav"),
    YOU_HAVE_REMOVED_STEM("YouHaveRemoved.wav"),
    YOU_HAVE_UPDATED_STEM("YouHaveUpdated.wav"),
    
    ADDED_CATEGORY("AddedCategory.wav"),
    ADDED_SUBSCRIPTION("AddedSubscription.wav"),
    ADDED_TRANSACTION("AddedTransaction.wav"),
    
    REMOVED_CATEGORY("RemovedCategory.wav"),
    REMOVED_SUBSCRIPTION("RemovedSubscription.wav"),
    REMOVED_TRANSACTION("RemovedTransaction.wav"),

    UPDATED_CATEGORY("UpdatedCategory.wav"),
    UPDATED_SUBSCRIPTION("UpdatedSubscription.wav"),
    UPDATED_TRANSACTION("UpdatedTransaction.wav"),

    ARE_YOU_SURE("AreYouSure.wav"),
    HAPPY_SAVING("HappySaving.wav"),

    // Graph
    GRAPH("LookAtThatGraph.wav"),

    // Budget
    NEARLY_OVER_BUDGET("NearlyOverBudget.wav"),
    OVER_YOUR_BUDGET("OverYourBudget.wav"),

    // Feeling
    DISAPPOINTED("Disappointed.wav"),
    WELL_DONE("WellDone.wav");


    @Getter
    private String file;

    @Getter
    private static Sound[] values = values();

    Sound(String file) {
        this.file = file;
    }
}
