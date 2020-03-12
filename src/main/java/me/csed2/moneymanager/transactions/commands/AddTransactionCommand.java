package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryRepository;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.TransactionBuilder;

import java.util.Date;

public class AddTransactionCommand implements ICommand<Boolean> {

    private final String categoryName;
    private final String name;
    private final int amount;
    private final String vendor;
    private final String[] notes;

    public AddTransactionCommand(String categoryName, String name, int amount, String vendor, String[] notes) {
        this.categoryName = categoryName;
        this.name = name;
        this.amount = amount;
        this.vendor = vendor;
        this.notes = notes;
    }

    @Override
    public Boolean execute() {
        CategoryRepository repository = CategoryRepository.getInstance();
        Category category = repository.readByName(categoryName);

        if (categoryName != null) {
            TransactionBuilder builder = new TransactionBuilder(name)
                    .withDate(new Date())
                    .withAmount(amount)
                    .withCategoryID(category.getId())
                    .withVendor(vendor)
                    .withNotes(notes);

            category.addTransaction(builder.build());
            repository.update(category);
            repository.save();
            return true;
        } else {
            return false;
        }
    }
}
