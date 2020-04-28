package homework.one.task01;

public class FirstTask {

    Integer[] intArray = {1, 2, 3, 4, 5};
    Double[] dblArray = {1.0, 2.0, 3.0, 4.0, 5.0};
    String[] strArray = {"One", "Two", "Three", "Four", "Five"};

    ArrayObject<Integer> intArrObj = new ArrayObject<>(intArray);
    ArrayObject<Double> dblArrObj = new ArrayObject<>(dblArray);
    ArrayObject<String> strArrObj = new ArrayObject<>(strArray);

    public void run() {
        int fstElement = 1;
        int sndElement = 3;

        System.out.println(intArrObj.toString());
        intArrObj.changePosition(fstElement, sndElement);
        System.out.println(intArrObj.toString());
        System.out.println();

        fstElement = 0;
        sndElement = 4;

        System.out.println(dblArrObj.toString());
        dblArrObj.changePosition(fstElement, sndElement);
        System.out.println(dblArrObj.toString());
        System.out.println();

        fstElement = 2;
        sndElement = 3;

        System.out.println(strArrObj.toString());
        strArrObj.changePosition(fstElement, sndElement);
        System.out.println(strArrObj.toString());
        System.out.println();
    }

}
