package me.csed2.moneymanager;

import me.csed2.moneymanager.main.App;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * This class schedules an asynchronous task to automatically save the categories after a certain amount of time.
 *
 * @author Ollie
 * @since 11/03/2020
 */
public class AutoSave {

    /**
     * The service scheduling the task
     */
    private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    /**
     * The task to autosave
     */
    private ScheduledFuture<?> future;

    /**
     * The amount of time between scheduling the task
     */
    private final long time;

    /**
     * The unit of time
     */
    private final TimeUnit units;

    /**
     * Default constructor for AutoSave. Initialises the variables.
     *
     * @param time The amount of time between scheduling the task
     * @param units The unit of time for the amount
     */
    public AutoSave(long time, TimeUnit units) {
        this.time = time;
        this.units = units;
    }

    /**
     * Schedules the task to begin.
     */
    public void start() {
        future = service.scheduleAtFixedRate(() -> {
            System.out.println("Saving...");
            App.getInstance().getCategoryCache().save("categories.json");
            System.out.println("Saved!");
        }, time, time, units);
    }

    /**
     * Closes the auto-save future and initialises a shutdown on the service.
     */
    public synchronized void interrupt() {
        future.cancel(true);
        service.shutdownNow(); // Ensures the process has shut down
        service.shutdown(); // Shut down the ExecutorService
    }
}
