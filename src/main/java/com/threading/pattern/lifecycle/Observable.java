package com.threading.pattern.lifecycle;

/**
 * @author Alex
 * @since 2019/05/28
 */
public interface Observable {


    enum LifeCycle {
        STARTED,
        RUNNING,
        COMPLETED,
        ERROR
    }

    /**
     * Get the lifecycle of current task
     * @return
     */
    LifeCycle getLifeCycle();

    void start();
}
