package com.epam.jmp.task3;


import java.util.Random;

public class Producer implements Runnable {

    private final MessageBusQueue queue;
    private final String name;
    private final String[] topics;
    private final Random random = new Random();

    public Producer(MessageBusQueue queue, String name, String[] topics) {
        this.queue = queue;
        this.name = name;
        this.topics = topics;
    }

    @Override
    public void run() {
        int counter = 1;
        System.out.println(name + " started");

        try {
            while (!Thread.currentThread().isInterrupted()) {
                String topic = topics[random.nextInt(topics.length)];
                String payload = name + " message #" + counter++;
                queue.put(new Message(topic, payload));
                System.out.println(name + " producing topic=" + topic + ", payload=" + payload);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            System.out.println( name + " interrupted");
            Thread.currentThread().interrupt();
        }
    }
}

