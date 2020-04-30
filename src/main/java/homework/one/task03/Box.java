package homework.one.task03;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    ArrayList<T> boxFruits = new ArrayList<>();
    String typeBoxFruit = "" ;

    /* Метод добавляет фрукт в коробку. */
    public void addFruit(T fruit) {
        boxFruits.add(fruit);
        if (typeBoxFruit.isEmpty()) {
            typeBoxFruit = fruit.getClass().getSimpleName();
        }
    }

    /* Метод удаляет фрукт из коробки. */
    public void removeFruit(T fruit) {
        boxFruits.remove(fruit);
    }

    /* Метод возвращает вес коробки с фруктами. */
    public double getWeight() {
        if (!boxFruits.isEmpty()) {
            double boxWeight = 0;
            for (T fruit : boxFruits) {
                boxWeight += fruit.getWeight();
            }
            return boxWeight;
        }
        return 0.0;
    }

    /* Метод сравнения коробок с фруктами по весу. */
    public boolean compare(Box o) {
        return Math.abs(getWeight() - o.getWeight()) < 0.0001;
    }

    /* Метод перемещает фрукты одного вида из одной коробки в другую.
     *  перемещать фрукты одного вида в коробку с фруктами другого вида не возможно. */
    public void onTransferFruits(Box<T> boxFruits, int countFruit) {
        for (int index = 0; index < countFruit; index++) {
            T fruit = this.boxFruits.get(index);
            this.removeFruit(fruit);
            boxFruits.addFruit(fruit);
        }
    }

    @Override
    public String toString() {
        return "Box<" + typeBoxFruit + ">" ;
    }
}
