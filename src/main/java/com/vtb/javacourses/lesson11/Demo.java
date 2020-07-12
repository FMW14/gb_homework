package com.vtb.javacourses.lesson11;

public class Demo {
    public Demo() {
    }

    @BeforeSuite
    public void method1() {
        System.out.println("m1");
    }

//    @BeforeSuite
//    @AfterSuite
//    public void testError(){
//        System.out.println("1111");
//    }

    @AfterSuite
    public void method2() {
        System.out.println("m2");
    }

    @Test(priority = 5)
    public void method5() {
        System.out.println("m5");
    }

    @Test(priority = 1)
    public void method3() {
        System.out.println("m3");
    }

    @Test(priority = 3)
    public void method4() {
        System.out.println("m4");
    }


}
