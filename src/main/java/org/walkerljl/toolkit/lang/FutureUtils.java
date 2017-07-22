package org.walkerljl.toolkit.lang;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author lijunlin
 */
public class FutureUtils {

    /**
     * Cancel future list
     *
     * @param futures
     * @param mayInterruptIfRunning
     */
    public static void cancel(List<Future<?>> futures, boolean mayInterruptIfRunning) {
        if (futures == null || futures.isEmpty()) {
            return;
        }
        for (Future<?> future : futures) {
            future.cancel(mayInterruptIfRunning);
        }
    }

    /**
     * Consume future
     *
     * @param future
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static Object consume(Future<?> future) throws ExecutionException, InterruptedException {
        if (future == null) {
            return null;
        }
        return future.get();
    }
}
