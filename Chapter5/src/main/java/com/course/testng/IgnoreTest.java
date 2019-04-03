package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {
    @Test(enabled = false)
    public void ignore1(){
        System.out.println("ignore1");
    }
    @Test
    public void ignore2(){
        System.out.println("ignore2");
    }
}
