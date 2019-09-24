package com.smxy.rxt.sys.service;

import com.smxy.rxt.common.service.AbstractService;
import com.smxy.rxt.sys.mapper.receiptMapper;
import com.smxy.rxt.sys.model.receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class receiptService extends AbstractService<receipt> {
    @Autowired(required = false)
    receiptMapper receiptMapper;

    public void addreceipt(Integer userid,Integer orderid){
        receipt receipt = new receipt();
        Date date = new Date();//获取当前时间    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        receipt.setCreattime(calendar.getTime());
        receipt.setUserid(userid);
        receipt.setOrderid(orderid);
        calendar.add(Calendar.MONTH, +1);
        receipt.setEnddate(calendar.getTime());
        this.add(receipt);
    }
    public List<Map<String,Object>> getReceipt(Integer pageNum, Integer pageSize,Integer userid){
        int page = (pageNum-1)*pageSize;
        return receiptMapper.getReceipt(page,pageSize,userid);
    }

    public List<Map<String,Object>> getReceiptByuserId(Integer userid){
        return receiptMapper.getReceiptByuserId(userid);
    }
}
