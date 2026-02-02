package com.epam.jmp.task2.deadlock;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

/**
 * Uses LongAdder to maintain running totals for sum and sum of squares.
 * This approach avoids deadlocks by eliminating explicit locks and
 * allowing concurrent updates to the totals.
 * AtomicLong can be used as well, but LongAdder is more efficient
 */
public class DeadlockLongAdder {

    private static final LongAdder sum = new LongAdder();
    private static final LongAdder sumSquares = new LongAdder();

    public static void main(String[] args) {

        Thread writer = new Thread(() -> {
            Random random = new Random();
            while (true) {
                int x = random.nextInt(100);
                sum.add(x);
                sumSquares.add((long) x * x);
                sleep(50);
            }
        });

        Thread sumThread = new Thread(() -> {
            while (true) {
                System.out.println("Sum = " + sum.sum());
                sleep(300);
            }
        });

        Thread sqrtThread = new Thread(() -> {
            while (true) {
                double value = Math.sqrt(sumSquares.sum());
                System.out.println("Sqrt(sum of squares) = " + value);
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

