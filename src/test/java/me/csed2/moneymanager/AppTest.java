package me.csed2.moneymanager;

import lombok.Getter;
import me.csed2.moneymanager.cache.CachedList;
import me.csed2.moneymanager.categories.Category;
import me.csed2.moneymanager.main.App;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.Mockito.*;

public class AppTest {

    @Getter
    private static App app;

    static {
        app = mock(App.class);

        when(app.getCategoryCache()).thenReturn(buildMockCategoryCache());
    }

    private static CachedList<Category> buildMockCategoryCache() {
        CachedList<Category> categories = new CachedList<>();
        categories.add(new Category.Builder("Fun").withCreationDate(new Date()).withBudget(100).build());
        categories.add(new Category.Builder("Score").withCreationDate(new Date()).withBudget(1500).build());
        categories.add(new Category.Builder("Rent").withCreationDate(new Date()).withBudget(10000).build());

        return categories;
    }
}
