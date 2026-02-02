package com.epam.jmp.task3;

public class Consumer implements Runnable {

    private final MessageBusQueue queue;
    private final String name;
    private final String topic;

    public Consumer(MessageBusQueue queue, String name, String topic) {
        this.queue = queue;
        this.name = name;
        this.topic = topic;
    }

    @Override
    public void run() {
        System.out.println(name + " started (topic=" + topic + ")");

        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message msg = queue.take(topic);
                System.out.println(name + " topic=" + topic + " payload=" + msg.payload());
            } catch (InterruptedException e) {
                System.out.println( name + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
}

