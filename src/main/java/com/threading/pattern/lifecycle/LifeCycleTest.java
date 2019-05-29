package com.threading.pattern.lifecycle;

import java.util.stream.IntStream;

/**
 * @author Alex
 * @since 2019/05/29
 */
public class LifeCycleTest {
    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(e -> {
            Observable thread = new ObservableThread<>(() -> {
                System.out.println(e + " Hello, observable " + Thread.currentThread().getName());
                return "";
            });
            thread.start();
        });
    }

}
