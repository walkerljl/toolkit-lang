package org.walkerljl.toolkit.lang;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;

/**
 * @author lijunlin
 */
public class CompletionServiceUtils {

    /**
     * 等待执行结果
     *
     * @param taskAmount
     * @param completionService
     * @param <T>
     */
    public static <T> void await(int taskAmount, CompletionService<T> completionService) {
        if (taskAmount <= 0 || completionService == null) {
            return;
        }
        for (int i = 0; i < taskAmount; i++) {
            try {
                completionService.take().get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
