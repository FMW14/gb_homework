package com.vtb.javacourses.lesson11;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
    public static void start(Class<?> testClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Map<Byte, List<Method>> testMethodMap = new HashMap<>();
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
                if (m.getAnnotation(Test.class).priority() > 10 || m.getAnnotation(Test.class).priority() < 1) {
                    throw new RuntimeException("Priority must have a value from 1 to 10");
                }

                List<Method> methods = testMethodMap.get(m.getAnnotation(Test.class).priority());
                if (methods == null) {
                    methods = new ArrayList<>();
                }
                methods.add(m);

                testMethodMap.put(m.getAnnotation(Test.class).priority(), methods);
            }
        }

//        List<Map.Entry<Byte, Method>> toSort = new ArrayList<>(testMethodMap.entrySet());
//        toSort.sort(Map.Entry.comparingByKey());

        Constructor<?> cons = testClass.getConstructor(null);
        Object obj = cons.newInstance();

        System.out.println("Test class: " + testClass.getName());

        if (beforeSuiteMethod != null) {
            System.out.println("---Launching @BeforeSuite method...---");
            beforeSuiteMethod.invoke(obj);
        } else {
            System.out.println("@BeforeSuite method undefined");
        }

        System.out.println("---Launching @Test methods...---");

        Map<Byte, List<Method>> sortedMethods = new TreeMap<>(testMethodMap);
        for (Map.Entry<Byte, List<Method>> entry : sortedMethods.entrySet()) {
            for (Method m : entry.getValue()) {
                System.out.println("Launch method " +m.getName() + " with priority = " + entry.getKey());
                m.invoke(obj);
            }
        }

        if (afterSuiteMethod != null) {
            System.out.println("---Launching @AfterSuite method...---");
            afterSuiteMethod.invoke(obj);
        } else {
            System.out.println("@AfterSuite method undefined");
        }

    }
}
