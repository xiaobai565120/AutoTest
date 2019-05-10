package com.course.utils;

import com.course.model.InterFaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    public static String getUrl(InterFaceName name){
        String address = bundle.getString("test.url");
        String uri ="";
        //最终的测试地址
        String testUrl;
        if(name == InterFaceName.ADDUSER){
            uri=bundle.getString("addUser.uri");
        }
        if(name == InterFaceName.GETUSERINFO) {
            uri = bundle.getString("getUserInfo.uri");
        }
        if(name == InterFaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }
        if(name == InterFaceName.LOGIN){
            uri =bundle.getString("login.uri");
        }
        if(name == InterFaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }
        testUrl = address + uri;
        return testUrl;

    }
}
