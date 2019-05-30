package com.threading.pattern.immutable;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * @author Alex
 */
public final class ImmutableAccumulator {
    /**
     * Immutable once it is initialized in constructor.
     */
    private final int value;

    /**
     * Mutable init
     */
    private int init;

    public ImmutableAccumulator(int value, int init) {
        this.value = value;
        this.init = init;
    }


    public ImmutableAccumulator(ImmutableAccumulator accumulator, int value) {
        this.value = accumulator.getValue() + value;
    }

    public int getValue() {
        return value;
    }

    public int getInit() {
        return init;
    }


    /**
     * Return a new object. Do not change any state of the original object.
     * @param value
     * @return
     */
    public ImmutableAccumulator incrementDefensive(int value) {
        return new ImmutableAccumulator(this, value);
    }

    public int increment(int init) {
        this.init += init;
        return this.init;
    }

    public static void main(String[] args) {
        final ImmutableAccumulator accumulator1 = new ImmutableAccumulator(0, 0);
        final ImmutableAccumulator accumulator2 = new ImmutableAccumulator(0, 0);
        IntStream.range(0, 5)
                .forEach(i -> new Thread(() -> {
                    int inc = 0;
                    for (;;){

                        int old1 = accumulator1.getInit();
                        int mutable = accumulator1.increment(inc);
                        if (inc + old1 != mutable) {
                            //this line print out sometimes
                            System.out.printf("Mutable Error: %d + %d = %d \n",inc, old1,
                                    mutable);
                        }

                        int old2 = accumulator2.getValue();
                        int immutable = accumulator2.incrementDefensive(inc).getValue();
                        if (inc + old2 != immutable) {
                            //this line never print
                            System.out.printf("Immutable Error: %d + %d = %d \n",inc, old2,
                                    immutable);
                        }
                        inc++;
                        sleep();
                    }
                }).start());
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
