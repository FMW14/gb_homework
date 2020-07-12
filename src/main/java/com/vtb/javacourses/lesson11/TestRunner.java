package com.vtb.javacourses.lesson11;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
    public static void start(Class<?> testClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Map<Byte, Method> testMethodMap = new HashMap<>();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuiteMethod == null) {
                    beforeSuiteMethod = m;
                } else {
                    throw new RuntimeException("More than 1 method with annotation @BeforeSuite");
                }
            }

            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuiteMethod == null) {
                    afterSuiteMethod = m;
                } else {
                    throw new RuntimeException("More than 1 method with annotation @AfterSuite");
                }
            }

            if (m.isAnnotationPresent(Test.class)) {
                testMethodMap.put(m.getAnnotation(Test.class).priority(), m);
            }
        }

//        Map<Byte, Method> sortedMethod = new TreeMap<>(testMethodMap);
//        System.out.println(sortedMethod);

//        List<Map.Entry<Byte, Method>> toSort = new ArrayList<>(testMethodMap.entrySet());
//        toSort.sort(Map.Entry.comparingByKey());

        Constructor<?> cons = testClass.getConstructor(null);
        Object obj = cons.newInstance();

        System.out.println("Test class: " + testClass.getName());

        if (beforeSuiteMethod !=null){
            System.out.println("---Launching @BeforeSuite method...---");
            beforeSuiteMethod.invoke(obj);
        } else {
            System.out.println("@BeforeSuite method undefined");
        }

        System.out.println("---Launching @Test methods...---");
        testMethodMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(m -> {
            try {
                System.out.println("Launch method " + m.getValue().getName() + " with priority = " + m.getKey().toString());
                m.getValue().invoke(obj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });


        if (afterSuiteMethod != null){
            System.out.println("---Launching @AfterSuite method...---");
            afterSuiteMethod.invoke(obj);
        } else {
            System.out.println("@AfterSuite method undefined");
        }

    }
}
