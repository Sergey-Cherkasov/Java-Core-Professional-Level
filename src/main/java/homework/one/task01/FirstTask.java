package homework.one.task01;

import java.util.Arrays;

public class FirstTask<T> {

    T[] array;

    public FirstTask(T[] array) {
        this.array = array;
    }

    public void swapPosition(T[] array, int fstPosition, int sndPosition) {
        T fstElement = array[fstPosition];
        T sndElement = array[sndPosition];
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

//        fstElement = 0;
//        sndElement = 4;
//
//
//        System.out.println(Arrays.toString(array));
//        swapPosition(dblArray, fstElement, sndElement);
//        System.out.println(Arrays.toString(dblArray));
//        System.out.println();
//
//        fstElement = 2;
//        sndElement = 3;
//
//        System.out.println(Arrays.toString(strArray));
//        swapPosition(strArray, fstElement, sndElement);
//        System.out.println(Arrays.toString(strArray));
//        System.out.println();
    }

}
