package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000)//单位为毫秒值
    public void test1() throws InterruptedException {
        System.out.println("test");
        Thread.sleep(2000);
    }
}
