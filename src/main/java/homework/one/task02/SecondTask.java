package homework.one.task02;

import java.util.ArrayList;
import java.util.Collections;

public class SecondTask<T> {

    private final T[] array;
    ArrayList<T> arrayList = new ArrayList<>();

    public SecondTask(T[] array) {
        this.array = array;
    }

    public ArrayList<T> toArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        return arrayList;
    }


    public void run() {
        System.out.println("Task 02.");

        arrayList = toArrayList(array);
        System.out.println(String.format("ArrayList<%s> = %s%n",
                getType(), arrayList));
    }

    public String getType() {
        return array.getClass().getSimpleName().substring(0, array.getClass().getSimpleName().length() - 2);
    }
}
