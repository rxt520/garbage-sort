package com.smxy.rxt.sys.mapper;

import com.smxy.rxt.sys.model.SortMan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SortManMapper extends Mapper<SortMan> {
    public Integer toAddSortMan(SortMan man);
    public List<Map<String,Object>> sortManList();
    public Map<String,Object> findMan(Integer userid);
    public List<Map<String,Object>> sortManListByPage(Integer page, Integer pageSize);
}