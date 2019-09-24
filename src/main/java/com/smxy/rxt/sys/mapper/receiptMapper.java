package com.smxy.rxt.sys.mapper;

import com.smxy.rxt.sys.model.receipt;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface receiptMapper extends Mapper<receipt> {
    public List<Map<String,Object>> getReceipt(Integer page, Integer pageSize, Integer userid);
    public List<Map<String,Object>> getReceiptByuserId(Integer userid);
}