package store;

import java.util.concurrent.*;

public abstract class DataStore {

    private ExecutorService executorService;

    protected DataStore() {
        this.executorService = Executors.newCachedThreadPool();
    }

    protected DataStore(ExecutorService executorService) {
        this.executorService = executorService;
    }

    protected <T> CompletableFuture<T> runAsync(Callable<T> task) {
        return runAsyncOnExecutor(executorService, task);
    }

    protected <T> CompletableFuture runAsyncOnExecutor(ExecutorService executor, Callable<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        executorService.submit(() -> future.complete(task.call()));
        return future;
    }

}
