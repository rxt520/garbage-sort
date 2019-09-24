package com.smxy.rxt.sys.controller;

import com.smxy.rxt.common.util.SendMsg;
import com.smxy.rxt.sys.model.SortMan;
import com.smxy.rxt.sys.model.User;
import com.smxy.rxt.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.concurrent.TimeUnit;

@Controller
public class UserController {
    @Autowired(required = false)
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 注册
     * @param email
     * @param phone
     * @param username
     * @param password
     * @param address
     * @return
     */
    @ResponseBody
    @PostMapping("regist")
    public String regist(String email, String phone, String username, String password, String address){
        User user = new User(username,password,3,phone,email,address);
        Integer i = userService.Regist(user);
        if (i==1){
            return "1";
        }
        else{
            return "0";
        }
    }
    /**
     * 登录
     * @param userName
     * @param password
     * @param remember
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @PostMapping("loginToIndex")
    public String login(String userName, String password,String remember,HttpServletRequest request, HttpServletResponse response){
        User user = new User(userName,password);
        User loginUser = userService.login(user);
        if (loginUser!=null){
            if (remember.equals("true")){
                Cookie cname = new Cookie("cname",loginUser.getUsername());
                Cookie cpwd = new Cookie("cpsw",loginUser.getPassword());
                cname.setMaxAge(60*60*24*7);
                cpwd.setMaxAge(60*60*24*7);
                cname.setPath("/");
                cpwd.setPath("/");
                //响应请求 存入cookie
                response.addCookie(cname);
                response.addCookie(cpwd);
            }
            HttpSession session =  request.getSession();
            session.setAttribute("user",loginUser);
            if (loginUser.getUsertypeid()==1){
                return "0";
            }else {
                return "1";
            }
        }else {
            return "2";
        }
    }
    @RequestMapping("BeSortMan")
    public String BeSortMan(SortMan sortman){
        SortMan a = sortman;
        userService.toBeSortMan(sortman);
        return "login";
    }
    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping("sendCode")
    public String sendCode(String phone){
        SendMsg msg = new SendMsg();
        String code = msg.getCode(phone);
        msg.setRedis(phone,code,redisTemplate);
        return "1";
    }
    /**
     * 重置密码
     * @param username
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping("resetPassword")
    public String resetPassword(String username,String phone,String password,String code){
        SendMsg msg = new SendMsg();
        String code1 = msg.getRedis(phone,redisTemplate);
        if (code.equals(code1)){
            Integer i=userService.resetPassword(username,phone,password);
            if (i==1){
                return "1";
            }else {
                return "2";
            }
        }else {
            return "0";
        }
    }
}
