package com.epam.jmp.task1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapExperiment {

    public static void main(String[] args) throws InterruptedException {

        // Case 1: not  thread-safe  -> will throw ConcurrentModificationException)
        //Map<Integer, Integer> map = new HashMap<>();

        // Case 2: thread-safe with synchronization
        //Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

        // Case 3: thread-safe, best performance
        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        Thread writer = new Thread(() -> {
            int i = 0;
            while (true) {
                map.put(i, i);
                i++;
            }
        });

        Thread reader = new Thread(() -> {
            while (true) {
                int sum = 0;

                // For ConcurrentHashMap, we don't need external synchronization
                synchronized (map) {
                    for (Integer v : map.values()) {
                        sum += v;
                    }
                }

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
