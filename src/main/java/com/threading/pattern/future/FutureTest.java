package com.threading.pattern.future;

/**
 * @author Alex
 * @since 2019/08/02
 */
public class FutureTest {
    public static void main(String[] args) {
      FutureService<String, Integer> service = FutureService.newService();
      service.submit(() -> {
          System.out.println(Thread.currentThread() + " void result.");
      });

      service.submit(Integer::parseInt, "13256");

      service.submit(Integer::parseInt, "13256", outcome -> System.out.println("callback outcome " +
              "and do sth on outcome." + Thread.currentThread()));
    }
}
