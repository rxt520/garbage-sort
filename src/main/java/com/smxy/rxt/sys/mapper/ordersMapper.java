package com.smxy.rxt.sys.mapper;

import com.smxy.rxt.sys.model.orders;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ordersMapper extends Mapper<orders> {
    public List<Map<String,Object>> findOrderList(Integer page, Integer pageSize, String orderState);
    public List<Map<String,Object>> findAllOrderByState(String orderState);
    public Integer FindOrderIdByOutTranNo(String OutTradeNo);
    List<Map<String,Object>> getUserOrderList(Integer page, Integer pageSize,Integer userid);
    List<Map<String,Object>> getAllUserOrder(Integer userid);

}
