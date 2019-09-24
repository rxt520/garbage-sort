package com.smxy.rxt.sys.service;

import com.smxy.rxt.common.service.AbstractService;
import com.smxy.rxt.sys.mapper.ordersMapper;
import com.smxy.rxt.sys.model.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService  extends AbstractService<orders> {
    @Autowired(required = false)
    ordersMapper ordersMapper;

    public void addOrder(orders orders){
        this.add(orders);
    }
    public List<orders> list(){
        orders orders = new orders();
        orders.setState("待接单");
        return this.findList(orders);
    }

    public String updateOrderState(Integer orderid) {
        orders orders = new orders();
        orders.setOrderid(orderid);
        orders orders1 = this.findOne(orders);
        if (orders1.getState().equals("待接单")){
            orders.setState("已接单");
            this.update(orders);
            return "1";
        }else {
            return "0";
        }
    }
    public List<Map<String,Object>> findOrderList(Integer pageNum, Integer pageSize){
        int page = (pageNum-1)*pageSize;
        return ordersMapper.findOrderList(page,pageSize,"待接单");
    }

    public List<Map<String,Object>> findAllOrderByState(){
        return ordersMapper.findAllOrderByState("待接单");
    }

    public Integer FindOrderIdByOutTranNo(String OutTradeNo){
        return ordersMapper.FindOrderIdByOutTranNo(OutTradeNo);
    }

    public List<Map<String,Object>> getUserOrderList(Integer pageNum, Integer pageSize,Integer userid){
        int page = (pageNum-1)*pageSize;
        return ordersMapper.getUserOrderList(page,pageSize,userid);
    }

    public List<Map<String,Object>> getAllUserOrder(Integer userid){
        return ordersMapper.getAllUserOrder(userid);
    }
}
