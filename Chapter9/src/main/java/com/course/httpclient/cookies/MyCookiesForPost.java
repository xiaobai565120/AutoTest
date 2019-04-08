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
    //��ȡcookies��Ϣ
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
        //ƴ�����յĲ��Ե�ַ
        String baseurl = this.testurl + this.testpostwithcookiesuri;

        //����һ��client�����������з�����ִ��
        DefaultHttpClient client = new DefaultHttpClient();

        //����һ�������������������post����
        HttpPost post = new HttpPost(baseurl);

        //��Ӳ���
        JSONObject param = new JSONObject();
        param.put("name", "huhasan");
        param.put("age", "30");

        //��������ͷ��Ϣ
        post.setHeader("Content-Type", "application/json");

        //��������Ϣ��ӵ�������
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        //����һ��������������Ӧ����Ĵ洢
        String result;

        //����cookies��Ϣ
       client.setCookieStore(this.store);
        System.out.println(store);

        //ִ��post����
        HttpResponse response = client.execute(post);

        //��ȡ��Ӧ���
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
            //�������������жϷ��ؽ���Ƿ����Ԥ��
            //�����صĽ���ַ���ת����json����
            JSONObject resultJson = new JSONObject(result);

            //��ȡ���ֵ
            String success = (String) resultJson.get("huhansan");
            String status = (String) resultJson.get("status");
            //�����жϷ��ؽ����ֵ
            Assert.assertEquals("success",success);
            Assert.assertEquals("1",status);
        }
    }

