package com.epam.jmp.task2.deadlock;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A single List<Integer> is used as the shared collection.
 * A ReentrantLock is used to ensure thread safety and prevent deadlocks.
 * Can use tryLock(), fairness, timeouts. More control than synchronized
 * Each thread performs its task in an infinite loop with a short sleep interval.
 */
public class DeadlockReentrantLock {

    private static final List<Integer> numbers = new ArrayList<>();
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread writer = new Thread(() -> {
            Random random = new Random();
            while (true) {
                lock.lock();
                try {
                    numbers.add(random.nextInt(100));
                } finally {
                    lock.unlock();
                }
                sleep(50);
            }
        });

        Thread sumThread = new Thread(() -> {
            while (true) {
                int sum = 0;
                lock.lock();
                try {
                    for (int n : numbers) {
                        sum += n;
                    }
                } finally {
                    lock.unlock();
                }
                System.out.println("Sum = " + sum);
                sleep(300);
            }
        });

        Thread sqrtThread = new Thread(() -> {
            while (true) {
                double sumSquares = 0;
                lock.lock();
                try {
                    for (int n : numbers) {
                        sumSquares += n * n;
                    }
                } finally {
                    lock.unlock();
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

