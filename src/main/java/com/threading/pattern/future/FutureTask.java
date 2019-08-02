package com.threading.pattern.future;

/**
 * JDK {@link java.util.concurrent.FutureTask} use AQS.
 *
 * @author Alex
 */
public class FutureTask<T> implements Future<T> {
    /**
     * The result to return or exception to throw from get()
     */
    private T outcome;
    private boolean isDone = false;

    private final Object lock = new Object();

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public T get() throws InterruptedException {
        synchronized (lock) {
            //wait until task is done.
            while (!isDone) {
                lock.wait();
            }
        }
        return outcome;
    }

    /**
     * Set/Update {@code result} and {@code isDone} status.
     *
     * @param result
     */
    protected void finish(T result) {
        synchronized (lock) {
            if (isDone) {
                return;
            }
            this.outcome = result;
            this.isDone = true;
            lock.notifyAll();
        }
    }
}
