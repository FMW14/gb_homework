package com.vtb.javacourses.lesson21;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.vtb.javacourses.lesson21.repos", "com.vtb.javacourses.lesson21.services"})
public class ContextConfig {
}
