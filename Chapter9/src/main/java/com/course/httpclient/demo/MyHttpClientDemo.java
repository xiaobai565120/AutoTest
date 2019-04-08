package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;


public class MyHttpClientDemo {
    @Test
    public void test1(){
        //用来接收返回结果
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
        //这个是用来执行get方法的
        HttpClient client = new DefaultHttpClient();
        try {
           HttpResponse response= client.execute(get);
           result= EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        //用来接收返回结果
        String result;
        HttpGet get = new HttpGet("http://localhost:8888/get/cookies");
        //这个是用来执行get方法的
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response= client.execute(get);
            result= EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
