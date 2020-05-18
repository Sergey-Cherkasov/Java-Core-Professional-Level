package homework.four;

public class Main {
    private static final Object monitor = new Object();
    private static char currentLetter = 'A';

    public static void main(String[] args) {

        Thread t1 = printLetter('A', 'B');
        Thread t2 = printLetter('B', 'C');
        Thread t3 = printLetter('C', 'A');

        t1.start();
        t2.start();
        t3.start();

    }

    private static Thread printLetter(char a, char b) {
        return new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != a) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(currentLetter);
                    currentLetter = b;
                    monitor.notifyAll();
                }
            }
        });
    }

}
