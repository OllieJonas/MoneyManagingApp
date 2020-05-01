package me.csed2.moneymanager.categories.commands;

import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.categories.CategoryArgType;
import me.csed2.moneymanager.main.App;

import java.util.Comparator;
import java.util.function.Function;

public class SortCategoriesCommand implements Function<App, CachedList<Category>> {

    private CategoryArgType argType;
    private boolean reversed;

    public SortCategoriesCommand(CategoryArgType argType, boolean reversed){
        this.argType = argType;
        this.reversed = reversed;
    }

    @Override
    public CachedList<Category> apply(App app) {
        Comparator<Category> comparator = getComparator(argType);
        CachedList<Category> sortedList = app.getCategoryCache().sort(comparator);
        app.render(sortedList.getReport());
        return sortedList;
    }

    private Comparator<Category> getComparator(CategoryArgType argType) {
        if(argType == CategoryArgType.NAME){
            return Comparator.comparing(Category::getName);
        }
        else if(argType == CategoryArgType.BUDGET){
            return Comparator.comparingDouble(Category::getBudget);
        }
        else if(argType == CategoryArgType.ID){
            return Comparator.comparingInt(Category::getId);
        }
        else if(argType == CategoryArgType.CREATED){
            return Comparator.comparing(Category::getDate);
        }
        else{
            return null;
        }
    }
}
