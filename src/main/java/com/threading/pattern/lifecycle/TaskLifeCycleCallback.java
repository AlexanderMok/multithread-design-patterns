package com.threading.pattern.lifecycle;

/**
 * LifeCycle callback interface
 * @author Alex
 * @since 2019/05/28
 */
public interface TaskLifeCycleCallback<T> {
    void onStart(Thread thread);
    void onRunning(Thread thread);
    void onCompleted(Thread thread, T result);
    void onError(Thread thread, Exception e);
}
