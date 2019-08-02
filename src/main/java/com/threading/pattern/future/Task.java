package com.threading.pattern.future;

/**
 * @author Alex
 */
@FunctionalInterface
public interface Task<I, O> {
    O get(I input);
}
