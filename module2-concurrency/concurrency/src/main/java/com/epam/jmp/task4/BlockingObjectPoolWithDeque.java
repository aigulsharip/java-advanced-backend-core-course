package com.epam.jmp.task4;

import java.util.ArrayDeque;
import java.util.Deque;

public class BlockingObjectPoolWithDeque {

    private final Deque<Object> pool = new ArrayDeque<>();
    private final int capacity;

    public BlockingObjectPoolWithDeque(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }
        this.capacity = size;

        for (int i = 0; i < size; i++) {
            pool.addLast(new Object());
        }
    }


    public Object get() throws InterruptedException {
        synchronized (pool) {
            while (pool.isEmpty()) {
                pool.wait();
            }
            Object obj = pool.removeFirst();
            pool.notifyAll();
            return obj;
        }
    }

    public void take(Object object) throws InterruptedException {
        if (object == null) {
            throw new IllegalArgumentException("object must not be null");
        }

        synchronized (pool) {
            while (pool.size() >= capacity) {
                pool.wait();
            }
            pool.addLast(object);
            pool.notifyAll();
        }
    }
}
