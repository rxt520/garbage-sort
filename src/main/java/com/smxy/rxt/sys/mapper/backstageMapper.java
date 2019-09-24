package com.smxy.rxt.sys.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface backstageMapper {
    public List<Map<String,Object>> userList(Integer page, Integer pageSize);
    public Integer deleteSortManByUserId(Integer userId);
    public List<Map<String,Object>> getReceipt(Integer page, Integer pageSize);

}
