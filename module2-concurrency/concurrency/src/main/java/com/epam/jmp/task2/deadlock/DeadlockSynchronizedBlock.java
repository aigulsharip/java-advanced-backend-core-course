package com.epam.jmp.task2.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A single List<Integer> is used as the shared collection.
 * All threads synchronize on the same lock object to ensure thread safety and prevent deadlocks.
 * Each thread performs its task in an infinite loop with a short sleep interval.
 */

public class DeadlockSynchronizedBlock {

    private static final List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {

        // Thread 1: Add random numbers
        Thread writer = new Thread(() -> {
            Random random = new Random();
            while (true) {
                synchronized (numbers) {
                    numbers.add(random.nextInt(100));
                }
                sleep(50);
            }
        });

        // Thread 2: Print sum
        Thread sumThread = new Thread(() -> {
            while (true) {
                int sum = 0;
                synchronized (numbers) {
                    for (int n : numbers) {
                        sum += n;
                    }
                }
                System.out.println("Sum = " + sum);
                sleep(300);
            }
        });

        // Thread 3: Print sqrt of sum of squares
        Thread sqrtThread = new Thread(() -> {
            while (true) {
                double sumSquares = 0;
                synchronized (numbers) {
                    for (int n : numbers) {
                        sumSquares += n * n;
                    }
                }
                System.out.println("Sqrt(sum of squares) = " + Math.sqrt(sumSquares));
                sleep(300);
            }
        });

        writer.start();
        sumThread.start();
        sqrtThread.start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
