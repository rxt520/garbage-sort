package com.smxy.rxt.sys.controller;

import com.smxy.rxt.common.model.R;
import com.smxy.rxt.sys.model.SortMan;
import com.smxy.rxt.sys.model.User;
import com.smxy.rxt.sys.model.receipt;
import com.smxy.rxt.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class backstageController {
    @Autowired(required = false)
    backstageService backstageService;
    @Autowired
    UserService userService;
    @Autowired(required = false)
    TypeService typeService;
    @Autowired(required = false)
    SortManService sortManService;
    @Autowired
    OrderService orderService;
    @Autowired(required = false)
    receiptService receiptService;
    @RequestMapping("user_add")
    public String user_add(){
        return "user_add";
    }
    @RequestMapping("user_role_add")
    public String user_role_add(){ return "user_role_add"; }
    @RequestMapping("sortMan_list")
    public String sortMan_list(){
        return "sortMan_list";
    }
    @RequestMapping("user_list")
    public String user_list(){
        return "user_list";
    }
    @RequestMapping("receipt_list")
    public String receipt_list(){return "receipt_list";}
    /**
     * 获取修改用户的信息
     * @param userId
     * @param request
     * @return
     */
    @RequestMapping("user_update")
    public String user_update(Integer userId, HttpServletRequest request){
        User user = new User();
        user.setUserid(userId);
        User user1 = userService.findOne(user);
        request.setAttribute("user",user1);
        return "user_update";
    }
    /**
     * 查询用户并分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("userlist")
    public Map userlist(Integer pageNum, Integer pageSize){
        List<Map<String,Object>> list = backstageService.userList(pageNum,pageSize);
        Map map = new HashMap();
        map.put("rows",list);
        map.put("total",userService.findAll().size());
        return map;
    }
    /**
     * 修改用户资料
     * @param userid
     * @param mobile
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping("update_User")
    public String updateUser(Integer userid,String mobile,String email){
        User user = new User();
        user.setUserid(userid);
        user.setPhone(mobile);
        user.setEmail(email);
        userService.update(user);
        return "0";
    }

    /**
     * 添加用户
     * @param username
     * @param mobile
     * @param password
     * @param email
     * @param address
     * @return
     */
    @ResponseBody
    @RequestMapping("add_user")
    public String add_user(String username,String mobile,String password,String email,String address){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhone(mobile);
        user.setPassword(password);
        user.setUsertypeid(3);
        userService.add(user);
        return "0";
    }
    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @GetMapping("delete_user")
    @ResponseBody
    public String delete(Integer[] ids) {
        for (Integer id : ids) {
            userService.deleteById(id);
        }
        return "0";
    }
    /**
     * 查询分类员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("sortManList")
    @ResponseBody
    public Map getAllSortMan(Integer pageNum, Integer pageSize){
        Map map = new HashMap();
        List<Map<String,Object>> list=sortManService.sortManListByPage(pageNum,pageSize);
        map.put("rows",list);
        map.put("total",sortManService.sortManList().size());
        return map;
    }
    /**
     * 批量删除用户
     * @param ids
     * @return
     */
    @GetMapping("delete_sortMan")
    @ResponseBody
    public String delete_sortMan(Integer[] ids) {
        for (Integer id : ids) {
            backstageService.deleteSortManByUserId(id);
            User user = new User();
            user.setUserid(id);
            user.setUsertypeid(3);
            userService.update(user);
        }
        return "0";
    }
    /**
     * 查询已被接受的订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("receiptList")
    @ResponseBody
    public Map getAllreceipt(Integer pageNum, Integer pageSize){
        Map map = new HashMap();
        List<Map<String,Object>> list=backstageService.getReceipt(pageNum,pageSize);
        map.put("rows",list);
        map.put("total",receiptService.findAll().size());
        return map;
    }
}
