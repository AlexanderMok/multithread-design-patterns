package com.threading.pattern.lifecycle;

/**
 * @author Alex
 * @since 2019/05/29
 */
public class NoOpLifeCycleCallback<T> implements TaskLifeCycleCallback<T> {
    @Override
    public void onStart(Thread thread) {
        System.out.printf("Started thread %s \n", thread.getName());
    }

    @Override
    public void onRunning(Thread thread) {
        System.out.printf("Running thread %s \n", thread.getName());
    }

    @Override
    public void onCompleted(Thread thread, T result) {
        System.out.printf("Completed thread %s \n", thread.getName());
    }

    @Override
    public void onError(Thread thread, Exception e) {
        System.out.printf("Error thread %s \n", thread.getName());
    }
}
