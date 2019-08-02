package com.threading.pattern.future;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

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

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public T get() throws InterruptedException {
        lock.lock();
        try {
            //wait until task is done.
            while (!isDone) {
                lock.wait();
            }
        } finally {
            lock.unlock();
        }
        return outcome;
    }

    /**
     * Set/Update {@code result} and {@code isDone} status.
     * @param result
     */
    protected void finish(T result) {
        lock.lock();
        try {
            if (isDone) {
                return;
            }
            this.outcome = result;
            this.isDone = true;
            lock.notifyAll();
        } finally {
            lock.unlock();
        }
    }
}
