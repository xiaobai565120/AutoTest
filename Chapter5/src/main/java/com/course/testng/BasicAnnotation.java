package com.course.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasicAnnotation {
    @Test
    public void testCase1(){
        System.out.println("testCase1");
    }
    @Test
    public void testCase2(){
        System.out.println("testCase2");
    }
    @BeforeMethod
    public void beforeMethod(){
       System.out.println("beforeMethod");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod");
    }
}
