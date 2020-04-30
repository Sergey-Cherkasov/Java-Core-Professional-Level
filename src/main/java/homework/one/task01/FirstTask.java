package homework.one.task01;

import java.util.Arrays;

public class FirstTask {

    Object[] array;

    public FirstTask(Object[] array) {
        this.array = array;
    }

    public void swapPosition(Object[] array, int fstPosition, int sndPosition) {
        Object fstElement = array[fstPosition];
        Object sndElement = array[sndPosition];
        array[fstPosition] = sndElement;
        array[sndPosition] = fstElement;
    }

    public void run() {
        int fstElement = 1;
        int sndElement = 3;

        System.out.println("Task 01.");

        System.out.println(Arrays.toString(array));
        swapPosition(array, fstElement, sndElement);
        System.out.println(Arrays.toString(array));
        System.out.println();
    }

}
