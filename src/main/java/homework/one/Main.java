package homework.one;

import homework.one.task01.FirstTask;
import homework.one.task02.SecondTask;
import homework.one.task03.ThirdTask;

public class Main {

    public static void main(String[] args) {

//        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] dblArray = {1.0, 2.0, 3.0, 4.0, 5.0};
        String[] strArray = {"One", "Two", "Three", "Four", "Five"};

//        FirstTask<Integer> fstTask = new FirstTask<>(intArray);
        FirstTask<String> fstTask = new FirstTask<>(strArray);
        SecondTask<Double> sndTask = new SecondTask<>(dblArray);
//        SecondTask<String> sndTask = new SecondTask<>(strArray);
        ThirdTask thdTask = new ThirdTask();

        fstTask.run();
        sndTask.run();
        thdTask.runTaskThird();

    }

}
