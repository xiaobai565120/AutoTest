package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @ApiOperation(value = " 登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i = sqlSessionTemplate.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询到的结果是"+i);
        if(i==1){
            log.info("登录的用户名是："+user.getUserName()+";"+"登录的密码是："+user.getPassword());
            return true;
        }
        return false;

    }

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        int result = 0;
        if(x!=null){
            result = sqlSessionTemplate.selectOne("addUser",user);
        }
        if(result >= 1){
            log.info("添加用户的数量是："+result);
            return true;
        }
        return false;
    }
    @ApiOperation(value = "获取用户（列表）信息接口",httpMethod = "POST" )
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        if (x == true){
            List<User> users = sqlSessionTemplate.selectList("getUserInfo",user);
            log.info("获取到的用户数量是："+users.size());
            return users;
        }else{
            return null;
        }
    }
    @ApiOperation(value = "更新/删除用户信息",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        int result = 0;
        if (x==true) {
            result = sqlSessionTemplate.selectOne("updateUserInfo", user);
        }
            log.info("更新用户的条目为："+result);
            return result;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("Cookies为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("login")
                    &&cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
