package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;

public class GetTransactionFromDB implements ICommand<Transaction> {

    private int id;
    private Transaction transaction;

    public GetTransactionFromDB(int id) {
        this.id = id;
    }

    @Override
    public Transaction execute() {
        return null;
    }
}
