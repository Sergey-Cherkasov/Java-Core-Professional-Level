package homework.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayObject<T> {

    private final T[] array;

    public ArrayObject(T[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "ArrayObject<" + array.getClass().getSimpleName() +
                "> = " + Arrays.toString(array);
    }

    public void changePosition(int fstPosition, int sndPosition) {
        T fstElement = array[fstPosition];
        T sndElement = array[sndPosition];
        array[fstPosition] = sndElement;
        array[sndPosition] = fstElement;
    }

    public ArrayList<T> arrayToArrayList(){
        ArrayList<T> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        return arrayList;
    }

}
