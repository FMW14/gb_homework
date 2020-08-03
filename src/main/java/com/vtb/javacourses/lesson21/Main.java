package com.vtb.javacourses.lesson21;

import com.vtb.javacourses.lesson21.util.PrepareData;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);



        context.close();
    }
}
