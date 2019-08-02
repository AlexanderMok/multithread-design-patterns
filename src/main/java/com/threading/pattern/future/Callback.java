package com.threading.pattern.future;

/**
 * @author Alex
 */
@FunctionalInterface
public interface Callback<T> {
    void call(T t);
}
