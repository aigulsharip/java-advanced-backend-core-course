package com.epam.jmp.task4;

public class BlockingObjectPoolTest {

    public static void main(String[] args) {

        BlockingObjectPoolWithDeque pool = new BlockingObjectPoolWithDeque(2);
        //BlockingObjectPool pool = new BlockingObjectPool(2);

        Runnable worker = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " trying to get object");
                Object obj = pool.get();
                System.out.println(Thread.currentThread().getName() + " got object");

                // simulate work
                Thread.sleep(1000);

                pool.take(obj);
                System.out.println(Thread.currentThread().getName() + " returned object");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // create more threads than pool size
        for (int i = 1; i <= 5; i++) {
            new Thread(worker, "Worker-" + i).start();
        }
    }
}
