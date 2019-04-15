package com.course.httpclient.header;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyHeaderForPost {
    private ResourceBundle bundle;
    private String  testurl,testpostwithheaderuri;
    @Test
    public void test1() throws IOException {
        this.bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        this.testurl = bundle.getString("testurl");
        this.testpostwithheaderuri = bundle.getString("testpostwithheaderuri");

        String baseurl = testurl + testpostwithheaderuri;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(baseurl);
        post.setHeader("content-type","application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","xiaobai");
        jsonObject.put("age","18");
        StringEntity entity = new StringEntity(jsonObject.toString());
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

}
