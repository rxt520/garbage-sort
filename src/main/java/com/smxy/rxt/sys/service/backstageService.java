package com.smxy.rxt.sys.service;

import com.smxy.rxt.sys.mapper.backstageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Service
public class backstageService {
    @Autowired(required = false)
    private backstageMapper backstageMapper;

    public List<Map<String,Object>> userList(Integer pageNum, Integer pageSize){
        int page = (pageNum-1)*pageSize;
        return backstageMapper.userList(page,pageSize);
    }

    public Integer deleteSortManByUserId(Integer userId){
        return backstageMapper.deleteSortManByUserId(userId);
    }

    public List<Map<String,Object>> getReceipt(Integer pageNum, Integer pageSize){
        int page = (pageNum-1)*pageSize;
        return backstageMapper.getReceipt(page,pageSize);
    }
}
