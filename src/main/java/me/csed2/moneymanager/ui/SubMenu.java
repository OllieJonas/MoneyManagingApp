package me.csed2.moneymanager.ui;

public interface SubMenu<T extends Menu> {
    T getParent();
}
