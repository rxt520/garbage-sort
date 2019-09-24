package com.smxy.rxt.sys.controller;

import com.alibaba.fastjson.JSON;
import com.smxy.rxt.common.util.SendMsg;
import com.smxy.rxt.sys.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("index")
    public String toindex(){ return "index"; }
    @GetMapping("about")
    public String about(){ return "about"; }
    @GetMapping("news")
    public String news(){ return "news"; }
    @GetMapping("video")
    public String video(){ return "video"; }
    @GetMapping("training")
    public String training(){ return "training"; }
    @GetMapping("honor")
    public String honor(){ return "honor"; }
    @GetMapping("comment")
    public String comment(){ return "comment"; }
    @GetMapping("contact")
    public String contact(){ return "contact"; }
    @GetMapping("news_detail")
    public String news_detail(){ return "news_detail"; }
    @GetMapping("service")
    public String service(){ return "service"; }
    @GetMapping("toBeSortMan")
    public String toBeSortMan() { return "toBeSortMan"; }
    @RequestMapping("backindex")
    public String backindex(){ return "backindex"; }
    @RequestMapping("main")
    public String main(){ return "main"; }
    @RequestMapping("loginout")
    public String loginOut(HttpSession session){ session.invalidate();return "login"; }
    @RequestMapping("userOrder_list")
    public String userOrder_list(){ return "userOrder_list"; };
    @RequestMapping("login")
    public String login(HttpServletRequest request){
        return toLogin(request);
    }
    @RequestMapping("orderList")
    public String orderList1(){
        return "orderList1";
    }
    @RequestMapping("receiptListByUserId")
    public String receiptListByUserId(){
        return "receiptList";
    }
    @RequestMapping("searchGarbageSort_list")
    public String searchGarbageSort_list(String name1,HttpServletRequest request1){
        List<Map<String,Object>> list = null;
        if (name1==null){
            name1="";
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response;
        String url = "https://laji.lr3800.com/api.php?name="+name1;
        Request request = new Request.Builder().url(url).get().build();
        try {
            response = okHttpClient.newCall(request).execute();
            String json =response.body().string();
            Map maps = (Map) JSON.parse(json);
            list = (List<Map<String, Object>>) maps.get("newslist");

        } catch (IOException e) {
            e.printStackTrace();
        }
        request1.setAttribute("list",list);
        return "searchGarbageSort_list";
    };
    /**
     * 登录存用户名密码进cookie
     * @param request
     * @return
     */
    @RequestMapping("/")
    public String toLogin(HttpServletRequest request){
        Cookie[] cookies =  request.getCookies();
        String cname="";
        String cpsw="";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("cname")){
                    cname=cookie.getValue();
                    request.setAttribute("cname",cname);
                }
                if (cookie.getName().equals("cpsw")){
                    cpsw=cookie.getValue();
                    request.setAttribute("cpsw",cpsw);
                }
            }
        }
        return "login";
    }

    @RequestMapping("/searchSort")
    @ResponseBody
    public List<Map<String,Object>> list(String name){
        List<Map<String,Object>> list = null;
        if (name==null){
            name="";
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response;
        String url = "https://laji.lr3800.com/api.php?name="+name;
        Request request = new Request.Builder().url(url).get().build();
        try {
            response = okHttpClient.newCall(request).execute();
            String json =response.body().string();
            Map maps = (Map) JSON.parse(json);
            list = (List<Map<String, Object>>) maps.get("newslist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
