package homework.five;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {

    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final CyclicBarrier cb;
    private final Semaphore semaphore;
    private final String name;

    static AtomicInteger win_count = new AtomicInteger();

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cb, Semaphore semaphore) {
        this.race = race;
        this.speed = speed;
        this.cb = cb;
        this.semaphore = semaphore;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                if (race.getStages().get(i) instanceof Tunnel) {
                    semaphore.acquire();
                    race.getStages().get(i).go(this);
                    semaphore.release();
                } else {
                    race.getStages().get(i).go(this);
                }
            }
            if (win_count.incrementAndGet() == 1) {
                System.out.println(this.name + " - WIN");
            }
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
