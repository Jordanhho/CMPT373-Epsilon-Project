package store;

import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

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

    protected static<T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> com) {
        return CompletableFuture.allOf(com.toArray(new CompletableFuture[com.size()]))
            .thenApply(v -> com.stream()
                .map(CompletableFuture::join)
                .collect(toList())
            );
    }


}
