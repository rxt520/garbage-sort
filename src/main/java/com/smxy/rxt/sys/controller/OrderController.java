package com.smxy.rxt.sys.controller;

import com.jpay.util.StringUtils;
import com.smxy.rxt.common.controller.alipay.AliPayController;
import com.smxy.rxt.sys.model.User;
import com.smxy.rxt.sys.model.orders;
import com.smxy.rxt.sys.model.receipt;
import com.smxy.rxt.sys.service.OrderService;
import com.smxy.rxt.sys.service.receiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired(required = false)
    OrderService orderService;

    @Autowired(required = false)
    receiptService receiptService;

    @Autowired(required = false)
    private HttpServletResponse response;

    /**
     * 添加未支付订单
     * @param userid
     * @param cname
     * @param cphone
     * @param caddress
     * @param wage
     * @return
     */
    @GetMapping("ImOrders")
    @ResponseBody
    public Map addOrder(Integer userid, String cname, String cphone, String caddress, Integer wage){
        orders orders = new orders();
        orders.setUserid(userid);
        orders.setCname(cname);
        orders.setCphone(cphone);
        orders.setCaddress(caddress);
        orders.setState("待支付");
        orders.setWage(wage);
        String outTradeNo = StringUtils.getOutTradeNo();
        orders.setOuttradeno(outTradeNo);
        orderService.add(orders);
//        redirect:http://sort.smxy.online:9000/alipay/pcPay?outTradeNo="+outTradeNo+"&wage="+wage
        Map map = new HashMap();
        map.put("outTradeNo",outTradeNo);
        map.put("wage",wage);
        return map;
    }

    /**
     * 查找未接单的订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @GetMapping("findOrderList")
    public Map findOrderList(Integer pageNum, Integer pageSize){
        Map map = new HashMap();
        List<Map<String,Object>> orderList = orderService.findOrderList(pageNum,pageSize);
        map.put("rows",orderList);
        map.put("total",orderService.findAllOrderByState().size());
        return map;
    }

    /**
     * 接单
     * @param ids
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping("acceptOrder")
    public String acceptOrder(Integer[] ids,Integer userid){
        for (Integer id : ids) {
            orderService.updateOrderState(id);
            receiptService.addreceipt(userid,id);
        }
        return "0";
    }

    /**
     * 分页查询本userid下的接单
     * @param pageNum
     * @param pageSize
     * @param userid
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("receiptListByUserid")
    public Map receiptListByUserid(Integer pageNum, Integer pageSize,Integer userid,HttpServletRequest request){
        Map map = new HashMap();
        List<Map<String,Object>> receiptList =receiptService.getReceipt(pageNum,pageSize,userid);
        map.put("rows",receiptList);
        map.put("total",receiptService.getReceiptByuserId(userid).size());
        return map;
    }

    /**
     * 查询用户所发布的订单
     * @param pageNum
     * @param pageSize
     * @param userid
     * @param request
     * @return
     */
    @RequestMapping("getUserOrderList")
    @ResponseBody
    public Map getUserOrder(Integer pageNum, Integer pageSize,Integer userid,HttpServletRequest request){
        Map map = new HashMap();
        List<Map<String,Object>> userOrderList =orderService.getUserOrderList(pageNum,pageSize,userid);
        map.put("rows",userOrderList);
        map.put("total",orderService.getAllUserOrder(userid).size());
        return map;
    }

    @RequestMapping("pay_Order")
    @ResponseBody
    public Map pay_Order(Integer orderid,Integer wage){
        Map map = new HashMap();
        orders orders = new orders();
        orders.setOrderid(orderid);
        String outTradeNo = StringUtils.getOutTradeNo();
        orders.setOuttradeno(outTradeNo);
        orderService.update(orders);
        map.put("outTradeNo",outTradeNo);
        map.put("wage",wage);
        return map;
    }
}
