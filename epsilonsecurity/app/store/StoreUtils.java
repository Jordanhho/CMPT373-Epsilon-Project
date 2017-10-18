package store;

import java.util.concurrent.*;

public class StoreUtils {

    private static ExecutorService defaultExecutor = Executors.newCachedThreadPool();

    static <T> CompletableFuture<T> runAsync(Callable<T> task) {
        return runAsyncOnExecutor(defaultExecutor, task);
    }

    static <T> CompletableFuture runAsyncOnExecutor(ExecutorService executor, Callable<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        executor.submit(() -> future.complete(task.call()));
        return future;
    }

}
