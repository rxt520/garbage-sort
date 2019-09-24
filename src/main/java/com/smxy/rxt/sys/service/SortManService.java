package com.smxy.rxt.sys.service;

import com.smxy.rxt.common.service.AbstractService;
import com.smxy.rxt.sys.mapper.SortManMapper;
import com.smxy.rxt.sys.model.SortMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SortManService extends AbstractService<SortMan>{

    @Autowired(required = false)
    private SortManMapper sortManMapper;

    public List<Map<String,Object>> sortManList(){
        return sortManMapper.sortManList();
    }

    public Map<String,Object> findMan(Integer userid){
        return sortManMapper.findMan(userid);
    }

    public List<Map<String,Object>> sortManListByPage(Integer pageNum, Integer pageSize){
        int page = (pageNum-1)*pageSize;
        return sortManMapper.sortManListByPage(page,pageSize);
    }
}
