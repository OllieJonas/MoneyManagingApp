package me.csed2.moneymanager.command;

/**
 * This class is the functional interface that will be executed when creating the class.
 *
 * You can use this either as an anonymous class or in an inheritance form (both are shown in test cases under "TestCommand".
 *
 * To use this, you need to pass this through the CommandDispatcher class, which is able to execute these commands.
 *
 * @author Ollie
 * @since 03/03/2020
 * @see CommandDispatcher
 *
 * @param <T> The object you would like to return. If you'd like it to return nothing, then use the "Void" object
 */
@FunctionalInterface
public interface ICommand<T> {
    T execute();
}
