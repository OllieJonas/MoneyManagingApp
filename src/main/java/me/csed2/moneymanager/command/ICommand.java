package me.csed2.moneymanager.command;

public interface ICommand {
    void execute() throws CommandSyntaxException;
}
