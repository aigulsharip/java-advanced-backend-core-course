package com.epam.jmp.task3;

public class MessageBusMain {

    public static void main(String[] args) throws InterruptedException {

        MessageBusQueue queue = new MessageBusQueue(10);

        String[] topics = {"news", "alerts", "orders"};

        Thread producer1 = new Thread(new Producer(queue, "Producer 1", topics));
        Thread producer2 = new Thread(new Producer(queue, "Producer 2", topics));

        Thread consumerNews   = new Thread(new Consumer(queue, "Consumer 1", "news"));
        Thread consumerAlerts = new Thread(new Consumer(queue, "Consumer 2", "alerts"));
        Thread consumerOrders = new Thread(new Consumer(queue, "Consumer 3", "orders"));

        producer1.start();
        producer2.start();

        consumerNews.start();
        consumerAlerts.start();
        consumerOrders.start();

        Thread.sleep(5000);

        producer1.interrupt();
        producer2.interrupt();
        consumerNews.interrupt();
        consumerAlerts.interrupt();
        consumerOrders.interrupt();
    }
}

