package com.epam.jmp.task1;

public class SimpleMapTest {

    public static void main(String[] args) throws InterruptedException {

        // Simple HashMap -> will throw  ConcurrentModificationException
        //Map<Integer, Integer> map = new HashMap<>();

        // Custom synchronized map
        // SynchronizedMap map = new SynchronizedMap();

        // Custom copy-on-write map
         CustomCopyOnWriteMap map = new CustomCopyOnWriteMap();

        Thread writer = new Thread(() -> {
            int i = 0;
            while (true) {
                map.put(i, i);
                i++;
            }
        });

        Thread reader = new Thread(() -> {
            while (true) {
                /*
                // only for HashMap
                int sum = 0;
                for (int v : map.values()) {
                    sum += v;
                }
                */
                int sum = map.sumValues();
                System.out.println("Sum = " + sum);
            }
        });

        writer.start();
        reader.start();

        Thread.sleep(5000);
        writer.interrupt();
        reader.interrupt();
    }
}

