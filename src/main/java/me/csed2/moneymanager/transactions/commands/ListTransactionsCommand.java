package me.csed2.moneymanager.transactions.commands;

import me.csed2.moneymanager.command.ICommand;
import me.csed2.moneymanager.transactions.Transaction;

import java.util.List;

public class ListTransactionsCommand implements ICommand<List<Transaction>> {

    @Override
    public List<Transaction> execute() {
        return null;
    }
}
