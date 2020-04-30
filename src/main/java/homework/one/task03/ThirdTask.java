package homework.one.task03;

public class ThirdTask {

    /**
     * Вес одного яблока
     */
    static final float APPLE_WEIGHT = 1.0f;
    /**
     * Вес одного апельсина
     */
    static final float ORANGE_WEIGHT = 1.5f;

    public void runTaskThird() {

        Box<Apple> boxApples1 = new Box<>();     // Создание коробки для яблок
        Box<Orange> boxOrange = new Box<>();    // Создание коробки для апельсинов
        Box<Apple> boxApples2 = new Box<>();    // Создание второй коробки для яблок

        int countApples = 15;   // Количество яблок
        int countOrange = 10;   // Количество апельсинов

        System.out.println("Task 03.");

        /* Наполнение коробки яблоками */
        for (int i = 0; i < countApples; i++) {
            boxApples1.addFruit(new Apple(APPLE_WEIGHT));
        }

        /* Наполнение коробки апельсинами */
        for (int i = 0; i < countOrange; i++) {
            boxOrange.addFruit(new Orange(ORANGE_WEIGHT));
        }

        System.out.println(String.format("Вес %s = %.2f", boxApples1, boxApples1.getWeight()));
        System.out.println(String.format("Вес %s = %.2f", boxOrange, boxOrange.getWeight()));
        System.out.println("Коробки равны? " + boxApples1.compare(boxOrange));
        System.out.println();

        System.out.println(String.format("Вес %s = %.2f", boxApples1, boxApples1.getWeight()));

        boxApples1.onTransferFruits(boxApples2, 8);

        System.out.println(String.format("Вес %s = %.2f", boxApples1, boxApples1.getWeight()));
        System.out.println(String.format("Вес %s = %.2f", boxApples2, boxApples2.getWeight()));
        System.out.println("Коробки равны? " + boxApples1.compare(boxApples2));
    }


}
