package homework.seven;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
/*
//          Вариант с созданием объекта типа Class

            Class<?> c = SampleMethods.class;
            Class<SampleMethods> c = SampleMethods.class;
            start(c);
*/
            start(SampleMethods.class);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void start(@NotNull Class<?> obj) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object sampleMethods = obj.newInstance();
        Method[] foos = obj.getDeclaredMethods();
        ArrayList<Method> arrAllFoo = new ArrayList<>();
        ArrayList<Method> arrTestFoo = new ArrayList<>();
        int flagBeforeSuite = 0;
        int flagAfterSuite = 0;

        for (Method foo : foos) {
            if (foo.isAnnotationPresent(Test.class)) {
                arrTestFoo.add(foo);
            }
            if (foo.isAnnotationPresent(BeforeSuite.class)) {
                arrAllFoo.add(0, foo);
                flagBeforeSuite++;
            }
            if (foo.isAnnotationPresent(AfterSuite.class)) {
                arrAllFoo.add(foo);
                flagAfterSuite++;
            }
            if (flagBeforeSuite > 1 || flagAfterSuite > 1) {
                throw new RuntimeException("Methods with BeforeSuite and AfterSuite annotations" +
                        " must be in a single instance");
            }
        }

        if (flagBeforeSuite == 0) {
            arrAllFoo.addAll(0, sortTestAnnotation(arrTestFoo));
        } else {
            arrAllFoo.addAll(1, sortTestAnnotation(arrTestFoo));
        }

        for (Method foo : arrAllFoo) {
            foo.invoke(sampleMethods);
        }
    }

//    Метод возвращает сортированный ArrayList методов с аннотацией @Test в порядке возрастания

    private static ArrayList<Method> sortTestAnnotation(ArrayList<Method> arrFoo) {
        for (int i = arrFoo.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int currentPriority = arrFoo.get(j).getDeclaredAnnotation(Test.class).priority();
                int nextPriority = arrFoo.get(j + 1).getDeclaredAnnotation(Test.class).priority();
                if (currentPriority > nextPriority) {
                    Method tmp = arrFoo.get(j);
                    arrFoo.remove(j);
                    arrFoo.add(j + 1, tmp);
                }
            }
        }
        return arrFoo;
    }

}
