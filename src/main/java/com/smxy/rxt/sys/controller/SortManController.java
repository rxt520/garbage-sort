package com.smxy.rxt.sys.controller;

import com.smxy.rxt.sys.service.SortManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class SortManController {

    @Autowired(required = false)
    private SortManService sortManService;

    @GetMapping("team")
    public String team(HttpServletRequest request){
        List<Map<String,Object>> list=sortManService.sortManList();
        request.setAttribute("sortManList",list);
        return "team";
    }

    @GetMapping("team_detail")
    public String team_detail(Integer userId,HttpServletRequest request)
    {
        Map<String,Object> man= sortManService.findMan(userId);
        request.setAttribute("man",man);
        return "team_detail";
    }
}
