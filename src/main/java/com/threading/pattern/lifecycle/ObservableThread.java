package com.threading.pattern.lifecycle;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * Encapsulate a {@link TaskLifeCycleCallback} in this {@code ObservableThread}
 * @author Alex
 * @since 2019/05/28
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycleCallback<T> taskLifeCycleCallback;

    private final Callable<T> task;

    private LifeCycle lifeCycle;

    public ObservableThread(Callable<T> task) {
        this(new NoOpLifeCycleCallback<T>(), task);
    }

    public ObservableThread(TaskLifeCycleCallback<T> taskLifeCycleCallback, Callable<T> task) {
        super();
        this.taskLifeCycleCallback = taskLifeCycleCallback;
        Objects.requireNonNull(task, "task is required.");
        this.task = task;
    }


    @Override
    public LifeCycle getLifeCycle() {
        return this.lifeCycle;
    }


    @Override
    public final void run() {
        this.update(LifeCycle.STARTED, null, null);
        try {
            this.update(LifeCycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(LifeCycle.COMPLETED, result, null);
        } catch (Exception e) {
            this.update(LifeCycle.ERROR, null, e);
        }
    }

    private void update(LifeCycle lifeCycle, T result, Exception e) {
        if (taskLifeCycleCallback == null) {
            return;
        }
        this.lifeCycle = lifeCycle;
        try {
            switch (lifeCycle) {
                case STARTED:
                    taskLifeCycleCallback.onStart(currentThread());
                    break;
                case RUNNING:
                    taskLifeCycleCallback.onRunning(currentThread());
                    break;
                case COMPLETED:
                    taskLifeCycleCallback.onCompleted(currentThread(), result);
                    break;
                case ERROR:
                    taskLifeCycleCallback.onError(currentThread(), e);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
