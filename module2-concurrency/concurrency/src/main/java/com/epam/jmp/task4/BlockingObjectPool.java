package com.epam.jmp.task4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingObjectPool {
    private final BlockingQueue<Object> queue;

    public BlockingObjectPool(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }

        this.queue = new ArrayBlockingQueue<>(size);

        for (int i = 0; i < size; i++) {
            queue.add(new Object());
        }
    }

    public Object get() throws InterruptedException {
        return queue.take();
    }

    public void take(Object object) throws InterruptedException {
        if (object == null) {
            throw new IllegalArgumentException("object must not be null");
        }
        queue.put(object);
    }
}
