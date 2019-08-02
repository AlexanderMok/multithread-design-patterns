package com.threading.pattern.future;


/**
 * For submission
 *
 * @author Alex
 */
public interface FutureService<I, O> {
    /**
     * Submit a task that has no return value. Future.get always return null.
     *
     * @param runnable
     * @return
     */
    Future<?> submit(Runnable runnable);

    /**
     * Submit a task that has a return value of type {@code O}.
     *
     * @param task
     * @param input
     * @return
     */
    Future<O> submit(Task<I, O> task, I input);


    /**
     * Submit a task that has a return value of type {@code O} with a {@code Callback}.
     *
     * @param task
     * @param input
     * @param callback
     * @return
     */
    Future<O> submit(Task<I, O> task, I input, Callback<O> callback);

    /**
     * Default implementation.
     *
     * @param <I>
     * @param <O>
     * @return
     */
    static <I, O> FutureService<I, O> newService() {
        return new FutureServiceImpl<>();
    }
}
