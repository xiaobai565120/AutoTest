package com.course.httpclient.cookies;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyHttpClientGetCookies {

    private String testurl, testgetcookiesuri,testgetwithcookiesuri;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
    bundle =ResourceBundle.getBundle("application", Locale.CHINA);
    this.testurl = bundle.getString("testurl");
    this.testgetcookiesuri = bundle.getString("testgetcookiesuri");
    this.testgetwithcookiesuri =bundle.getString("testgetwithcookiesuri");


    }
    @Test
    public void test1() throws IOException{
        //用来接收返回结果
        String result;
        String baseurl=testurl+testgetcookiesuri;
        HttpGet get = new HttpGet(baseurl);
        //这个是用来执行get方法的
        HttpClient client = new DefaultHttpClient();
        HttpResponse response= client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

        //获取cookie信息
         this.store = ((DefaultHttpClient) client).getCookieStore();
        List<Cookie> cookielist=store.getCookies();

        for (Cookie cookie:cookielist) {
            String name = cookie.getName();
            String value =cookie.getValue();
            System.out.println(cookie);
            System.out.println("name="+name+";value="+value);

        }

    }
        @Test(dependsOnMethods = {"test1"})
        public void test2() throws IOException  {
            String result;
            String baseurl = testurl+testgetwithcookiesuri;
            HttpGet get = new HttpGet(baseurl);
            //这个是用来执行get方法的
            HttpClient client = new DefaultHttpClient();
            //设置cookies信息
            ((DefaultHttpClient) client).setCookieStore(this.store);
            HttpResponse response= client.execute(get);
            result= EntityUtils.toString(response.getEntity(),"UTF-8");
            System.out.println(result);

        }
    }

