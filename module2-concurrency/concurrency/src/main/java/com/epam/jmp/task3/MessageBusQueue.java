package com.epam.jmp.task3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MessageBusQueue {

    private final Queue<Message> queue = new LinkedList<>();
    private final int capacity;

    public MessageBusQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() >= capacity) {
                queue.wait();
            }
            queue.offer(message);
            queue.notifyAll();
        }
    }

    public Message take(String topic) throws InterruptedException {
        synchronized (queue) {
            while (true) {
                Iterator<Message> it = queue.iterator();
                while (it.hasNext()) {
                    Message msg = it.next();
                    if (topic.equals(msg.topic())) {
                        it.remove();
                        queue.notifyAll();
                        return msg;
                    }
                }
                queue.wait();
            }
        }

    }
}

