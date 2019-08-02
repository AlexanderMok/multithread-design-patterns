package com.threading.pattern.future;

/**
 * @author Alex
 */
public interface Future<T> {
    boolean isDone();

    T get() throws InterruptedException;
}
