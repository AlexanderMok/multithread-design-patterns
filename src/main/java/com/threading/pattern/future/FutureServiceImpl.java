package com.threading.pattern.future;

/**
 * Create threads for tasks.
 * @author Alex
 */
public class FutureServiceImpl<I, O> implements FutureService<I, O> {

    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";
    @Override
    public Future<?> submit(Runnable runnable) {
        return null;
    }

    @Override
    public Future<O> submit(Task<I, O> task, I input) {
        return null;
    }
}
