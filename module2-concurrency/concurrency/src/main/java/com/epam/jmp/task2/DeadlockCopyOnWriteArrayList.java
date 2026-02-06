package com.epam.jmp.task2;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Uses CopyOnWriteArrayList to handle concurrent modifications without explicit synchronization.
 * This approach avoids deadlocks by allowing safe iteration and modification of the list
 * without locking, at the cost of higher memory usage and potential performance overhead
 * during write operations.
 */
public class DeadlockCopyOnWriteArrayList {

    private static final CopyOnWriteArrayList<Integer> numbers =
            new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        Thread writer = new Thread(() -> {
            Random random = new Random();
            while (true) {
                numbers.add(random.nextInt(100));
                sleep(50);
            }
        });

        Thread sumThread = new Thread(() -> {
            while (true) {
                int sum = 0;
                for (int n : numbers) {
                    sum += n;
                }
                System.out.println("Sum = " + sum);
                sleep(300);
            }
        });

        Thread sqrtThread = new Thread(() -> {
            while (true) {
                double sumSquares = 0;
                for (int n : numbers) {
                    sumSquares += n * n;
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
