package com.threading.pattern.guardedsuspension;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Suspend current thread for a finite and reasonable period of time until
 * condition is satisfied.
 *
 * @author Alex
 */
public class GuardedSuspensionQueue<E> {
    private final int CAPCACITY_MAX = 100;
    private final Queue<E> queue = new ArrayDeque<>(CAPCACITY_MAX);

    public boolean offer(E item) {
        synchronized (this) {
            // suspend current thread while condition is not satisfied
            while (this.queue.size() >= CAPCACITY_MAX) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean o = queue.offer(item);
            this.notifyAll();
            return o;
        }
    }

    public E poll() {
        synchronized (this) {
            while (this.queue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            E e = queue.poll();
            this.notifyAll();
            return e;
        }
    }
}
