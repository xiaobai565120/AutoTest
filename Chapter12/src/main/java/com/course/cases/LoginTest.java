package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterFaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    @BeforeTest(groups = "loginTrue",description = "测试准备工作，获取httpclient对象")
    public void beforeTest(){
        TestConfig.getUserInfourl = ConfigFile.getUrl(InterFaceName.GETUSERINFO);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterFaceName.ADDUSER);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterFaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(InterFaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterFaceName.UPDATEUSERINFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();

    }
    @Test(groups = "loginTrue",description = "用户登录成功接口测试")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
    }
    @Test(groups = "loginFalse",description = "用户登录失败接口测试")
    public void loginFalse() throws IOException{
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());

    }
}
