package com.smxy.rxt.sys.model;

import java.util.Date;
import javax.persistence.*;

public class receipt {
    @Id
    private Integer receiptid;

    private Integer userid;

    private Integer orderid;

    private Date creattime;

    private Date enddate;

    /**
     * @return receiptid
     */
    public Integer getReceiptid() {
        return receiptid;
    }

    /**
     * @param receiptid
     */
    public void setReceiptid(Integer receiptid) {
        this.receiptid = receiptid;
    }

    /**
     * @return userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return orderid
     */
    public Integer getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     */
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    /**
     * @return creattime
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * @param creattime
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /**
     * @return enddate
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * @param enddate
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}