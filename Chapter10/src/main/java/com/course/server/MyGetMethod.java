package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {
    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求参数的类
        //HttpServerletResponse 装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息成功";

    }
    /**
     * 要求客户端携带cookies访问
     */
    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带cookies访问",httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
            Cookie[] cookies = request.getCookies();
            if(Objects.isNull(cookies)){
                return "你必须携带cookies信息来";
            }
        for (Cookie cookie:cookies
             ) {
            if(cookie.getName().equals("login")&&
                    cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息访问的的get请求";
            }

        }
        return "你必须携带cookies信息来";

    }
    /**
     * 开发一个需要携带参数的get请求
     * 第一种实现方式 url：key=value&key=value
     * 我们来模拟获取商品列表
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "一个需要携带参数的get请求方法一",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋1",400);
        myList.put("鞋2",400);
        myList.put("鞋3",400);
        myList.put("鞋4",400);
        myList.put("鞋5",400);
        return myList;

    }
    /**
     * 第二种携带参数访问的get请求
     * URL:ip：port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "第二种携带参数访问的get请求",httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋1",400);
        myList.put("鞋2",400);
        myList.put("鞋3",400);
        myList.put("鞋4",400);
        myList.put("鞋5",400);
        return myList;
    }
}
