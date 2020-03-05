package me.csed2.moneymanager.command;

public interface CommandCallback<T> {
    Void onSuccess(T result);
    Void onFailure(Throwable throwable);
}
