package homework.one.task01;

import java.util.Arrays;

public class ArrayObject<T> {

    private final T[] array;

    public ArrayObject(T[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "ArrayObject{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public void changePosition(int fstPosition, int sndPosition) {
        T fstElement = array[fstPosition];
        T sndElement = array[sndPosition];
        array[fstPosition] = sndElement;
        array[sndPosition] = fstElement;
    }
}
