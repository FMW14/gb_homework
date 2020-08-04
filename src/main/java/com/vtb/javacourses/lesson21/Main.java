package com.vtb.javacourses.lesson21;

import com.vtb.javacourses.lesson21.config.ContextConfig;
import com.vtb.javacourses.lesson21.services.ProductService;
import com.vtb.javacourses.lesson21.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfig.class);

        UserService userService = context.getBean("userService", UserService.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        System.out.println(userService.getAll());
        System.out.println(productService.getAll());

        context.close();
    }
}
