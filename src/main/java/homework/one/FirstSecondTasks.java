package homework.one;

import java.util.ArrayList;

public class FirstSecondTasks {

    Integer[] intArray = {1, 2, 3, 4, 5};
    Double[] dblArray = {1.0, 2.0, 3.0, 4.0, 5.0};
    String[] strArray = {"One", "Two", "Three", "Four", "Five"};

    ArrayObject<Integer> intArrObj = new ArrayObject<>(intArray);
    ArrayObject<Double> dblArrObj = new ArrayObject<>(dblArray);
    ArrayObject<String> strArrObj = new ArrayObject<>(strArray);

    ArrayList<Integer> intArrList = new ArrayList<>();
    ArrayList<Double> dblArrayList = new ArrayList<>();
    ArrayList<String> strArrayList = new ArrayList<>();

    public void runTaskOne() {
        int fstElement = 1;
        int sndElement = 3;

        System.out.println("Task 01.");

        System.out.println(intArrObj);
        intArrObj.changePosition(fstElement, sndElement);
        System.out.println(intArrObj);
        System.out.println();

        fstElement = 0;
        sndElement = 4;

        System.out.println(dblArrObj);
        dblArrObj.changePosition(fstElement, sndElement);
        System.out.println(dblArrObj);
        System.out.println();

        fstElement = 2;
        sndElement = 3;

        System.out.println(strArrObj);
        strArrObj.changePosition(fstElement, sndElement);
        System.out.println(strArrObj);
        System.out.println();
    }

    public void runTaskTwo() {
        System.out.println("Task 02.");

        intArrList = intArrObj.arrayToArrayList();
        System.out.println(String.format("ArrayList<Integer> = %s%n", intArrList));

        dblArrayList = dblArrObj.arrayToArrayList();
        System.out.println(String.format("ArrayList<Double> = %s%n", dblArrayList));

        strArrayList = strArrObj.arrayToArrayList();
        System.out.println(String.format("ArrayList<String> = %s%n", strArrayList));
    }
}
