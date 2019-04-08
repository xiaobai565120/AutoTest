package com.course.httpclient.cookies;


import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private ResourceBundle bundle;
    private String testurl,testgetcookiesuri,testpostwithcookiesuri;
    private CookieStore store;
    @Test
    //获取cookies信息
    public void test1() throws IOException{
        this.bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        this.testurl = bundle.getString("testurl");
        this.testgetcookiesuri = bundle.getString("testgetcookiesuri");
        this.testpostwithcookiesuri = bundle.getString("testpostwithcookiesuri");

        String baseurl =testurl + testgetcookiesuri;
        HttpGet get = new HttpGet(baseurl);
        HttpClient client1 =  new DefaultHttpClient();
        HttpResponse response = client1.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
       this.store = ((DefaultHttpClient) client1).getCookieStore();
        List<Cookie> cookielist = store.getCookies();
        for (Cookie cookie:cookielist
             ) {
            String name = cookie.getName();
            String value =cookie.getValue();
            System.out.println(cookie);
            System.out.println("name="+name+";value="+value);

        }

        }

    @Test(dependsOnMethods = {"test1"})
    public void test2() throws IOException {
        //拼接最终的测试地址
        String baseurl = this.testurl + this.testpostwithcookiesuri;

        //声明一个client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();

        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(baseurl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "huhasan");
        param.put("age", "30");

        //设置请求头信息
        post.setHeader("Content-Type", "application/json");

        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        //声明一个对象来进行相应结果的存储
        String result;

        //设置cookies信息
       client.setCookieStore(this.store);
        System.out.println(store);

        //执行post方法
        HttpResponse response = client.execute(post);

        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
            //处理结果，就是判断返回结果是否符合预期
            //将返回的结果字符串转化成json对象
            JSONObject resultJson = new JSONObject(result);

            //获取结果值
            String success = (String) resultJson.get("huhansan");
            String status = (String) resultJson.get("status");
            //具体判断返回结果的值
            Assert.assertEquals("success",success);
            Assert.assertEquals("1",status);
        }
    }

