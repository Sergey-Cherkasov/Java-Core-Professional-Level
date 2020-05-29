package homework.seven.checkhw;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class CheckingHomeWork {

    private static final int argYear = 2020;
    private static final int argInt = -7;
    private static final int[] argsInt = new int[]{1, 2, 3, 4};
    private static final float[] argsFloat = new float[]{1f, 2f, 3f, 4f};
    private static final String PATH_URL = "D:/homeworks";

    public static void main(String[] args) throws Exception {
        File file = new File(PATH_URL);
        String[] str = file.list();
        for (String s : str) {
            String[] mass = s.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(s, new Exception());
            }

            Class<?> c = URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(mass[0]);

            /* Объявляем экземпляр класса*/
            Object object = c.newInstance();

/*
            Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
            for (Constructor<?> declaredConstructor : declaredConstructors) {
                System.out.println(declaredConstructor);
            }
*/
            /* Выводим наименование проверяемого класса */
            System.out.println(c);

            /* Получаем массив методов в классе */
            Method[] declaredMethods = c.getDeclaredMethods();

            /*
             * Реализация простого решения проверки выполнения методов классов через рефлексию
             */
            int testResult = 0;
            boolean resultBoolean;
            int resultInt;
            float resultFloat;
            for (Method declaredMethod : declaredMethods) {
                declaredMethod.setAccessible(true);
                switch (declaredMethod.getName()) {
                    case "isLeapYear":
                        resultBoolean = (boolean) declaredMethod.invoke(object, argYear);
                        boolean argsResult = (argYear % 100 != 0) && (argYear % 4 == 0) || (argYear % 400 == 0);
                        if (resultBoolean && argsResult || !resultBoolean && !argsResult) {
                            testResult++;
                        }
                        break;
                    case "isNegative":
                        resultBoolean = (boolean) declaredMethod.invoke(object, argInt);
                        if (resultBoolean && argInt < 0 || !resultBoolean && argInt > 0) {
                            testResult++;
                        }
                        break;
                    case "checkTwoNumbers":
                        resultBoolean = (boolean) declaredMethod.invoke(object, argsInt[0], argsInt[1]);
                        if (resultBoolean) {
                            if ((argsInt[0] + argsInt[1]) >= 10 && (argsInt[0] + argsInt[1]) <= 20) {
                                testResult++;
                            }
                        } else {
                            if ((argsInt[0] + argsInt[1]) < 10 || (argsInt[0] + argsInt[1]) > 20) {
                                testResult++;
                            }
                        }
                        break;
                    case "calculate":
                        switch (declaredMethod.getReturnType().getName()) {
                            case "int":
                                resultInt = (int) declaredMethod.invoke(object, argsInt[0], argsInt[1], argsInt[2], argsInt[3]);
                                if (resultInt == 2) {
                                    testResult++;
                                }
                                break;
                            case "float":
                                resultFloat = (float) declaredMethod.invoke(object, argsFloat[0], argsFloat[1], argsFloat[2], argsFloat[3]);
                                if (resultFloat == 2.75f) {
                                    testResult++;
                                }
                        }
                }
            }
            System.out.printf("%s выполнил верно %d заданий(-я, -е)%n", c.getName(), testResult);

            /* Дополнительная реализация проверки выполнения методов класса */
            /* Формируем map методов с типами аргументов */
            Map<Method, Class<?>[]> mapMethods = getMethodsReturnResult(declaredMethods);

            for (Method method : mapMethods.keySet()) {
                Class<?>[] classesParam = mapMethods.get(method);
                method.setAccessible(true);
                switch (method.getReturnType().getName()) {
                    case "boolean":
                        methodBooleanResult(object, method, classesParam);
                        break;
                    case "int":
                        int intResult = methodIntResult(object, method, classesParam);
                        System.out.printf("%s : Input arguments - %d, %d, %d, %d. Result - '%d'%n",
                                method.getName(), argsInt[0], argsInt[1], argsInt[2], argsInt[3], intResult);
                        break;
                    case "float":
                        float floatResult = methodFloatResult(object, method, classesParam);
                        System.out.printf("%s : Input arguments - %.2f, %.2f, %.2f, %.2f. Result - '%.2f'%n",
                                method.getName(), argsFloat[0], argsFloat[1], argsFloat[2], argsFloat[3], floatResult);
                }
            }
            System.out.println();
        }
    }

    private static float methodFloatResult(Object object, Method method, Class<?>[] classesParam) throws IllegalAccessException, InvocationTargetException {
        if (classesParam[0].getName().equals("float") &&
                classesParam[1].getName().equals("float") &&
                classesParam[2].getName().equals("float") &&
                classesParam[3].getName().equals("float")) {
            return (float) method.invoke(object, argsFloat[0], argsFloat[1], argsFloat[2], argsFloat[3]);
        }
        return 0f;
    }

    private static int methodIntResult(Object object, Method method, Class<?>[] classesParam) throws IllegalAccessException, InvocationTargetException {
        if (classesParam[0].getName().equals("int") &&
                classesParam[1].getName().equals("int") &&
                classesParam[2].getName().equals("int") &&
                classesParam[3].getName().equals("int")) {
            return (int) method.invoke(object, argsInt[0], argsInt[1], argsInt[2], argsInt[3]);
        }
        return 0;
    }

    private static void methodBooleanResult(Object object, Method method, Class<?>[] classesParam) throws IllegalAccessException, InvocationTargetException {
        switch (classesParam.length) {
            case 1:
                if (classesParam[0].getName().equals("int")) {
                    boolean result = (boolean) method.invoke(object, argYear);
                    System.out.printf("%s : Input argument - %d. Result - '%b'%n",
                            method.getName(), argYear, result);
                }
                break;
            case 2:
                if (classesParam[0].getName().equals("int") &&
                        classesParam[1].getName().equals("int")) {
                    boolean result = (boolean) method.invoke(object, argsInt[0], argsInt[1]);
                    System.out.printf("%s : Input arguments - %d, %d. Result - '%b'%n",
                            method.getName(), argsInt[0], argsInt[1], result);
                }
                break;
        }
    }

    /* Getting a map of methods with arguments types that return the result */
    private static Map<Method, Class<?>[]> getMethodsReturnResult(Method[] declaredMethods) {
        Map<Method, Class<?>[]> mapMethods = new HashMap<>();
        for (Method declaredMethod : declaredMethods) {
            if (!"void".equals(declaredMethod.getAnnotatedReturnType().getType().getTypeName())) {
                mapMethods.put(declaredMethod, declaredMethod.getParameterTypes());
            }
        }
        return mapMethods;
    }

}
