package com.course.httpclient.post;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class HttpClientPostDemo {
    private String testurl, testposturi;
    private ResourceBundle bundle;
    @BeforeTest
    public void load(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        this.testurl = bundle.getString("testurl");
        this.testposturi = bundle.getString("testposturi");

    }
    @Test
    public void test1() {
        String baseurl = testurl+testposturi;
        HttpPost post = new HttpPost(baseurl);
        HttpClient client = new DefaultHttpClient();


    }

}
