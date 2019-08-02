package com.threading.pattern.future;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create threads for tasks.
 *
 * @author Alex
 */
public class FutureServiceImpl<I, O> implements FutureService<I, O> {

    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";
    private final AtomicInteger next = new AtomicInteger(0);

    private String nextThreadName() {
        return FUTURE_THREAD_PREFIX + next.getAndIncrement();
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> future = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            future.finish(null);
        }, nextThreadName()).start();
        return future;
    }

    @Override
    public Future<O> submit(Task<I, O> task, I input) {
        final FutureTask<O> future = new FutureTask<>();
        new Thread(() -> {
            O outcome = task.get(input);
            future.finish(outcome);
        }, nextThreadName()).start();
        return future;
    }

    @Override
    public Future<O> submit(Task<I, O> task, I input, Callback<O> callback) {
        final FutureTask<O> future = new FutureTask<>();
        new Thread(() -> {
            O outcome = task.get(input);
            future.finish(outcome);
            if (Objects.nonNull(callback)) {
                callback.call(outcome);
            }
        }, nextThreadName()).start();
        return future;
    }
}
