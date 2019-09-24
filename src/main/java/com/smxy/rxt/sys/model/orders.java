package com.smxy.rxt.sys.model;

import javax.persistence.*;

public class orders {
    @Id
    private Integer orderid;

    private Integer userid;

    private String cname;

    private String cphone;

    private String caddress;

    private String state;

    private Integer wage;

    @Column(name = "TradeNo")
    private String tradeno;

    @Column(name = "outTradeNo")
    private String outtradeno;

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
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return cphone
     */
    public String getCphone() {
        return cphone;
    }

    /**
     * @param cphone
     */
    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    /**
     * @return caddress
     */
    public String getCaddress() {
        return caddress;
    }

    /**
     * @param caddress
     */
    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return wage
     */
    public Integer getWage() {
        return wage;
    }

    /**
     * @param wage
     */
    public void setWage(Integer wage) {
        this.wage = wage;
    }

    /**
     * @return TradeNo
     */
    public String getTradeno() {
        return tradeno;
    }

    /**
     * @param tradeno
     */
    public void setTradeno(String tradeno) {
        this.tradeno = tradeno;
    }

    /**
     * @return outTradeNo
     */
    public String getOuttradeno() {
        return outtradeno;
    }

    /**
     * @param outtradeno
     */
    public void setOuttradeno(String outtradeno) {
        this.outtradeno = outtradeno;
    }


    public orders(Integer userid, String cname, String cphone, String caddress, String state) {
        this.userid = userid;
        this.cname = cname;
        this.cphone = cphone;
        this.caddress = caddress;
        this.state = state;
    }

    public orders() {
    }

    public orders(Integer orderid, Integer userid, String cname, String cphone, String caddress, String state) {
        this.orderid = orderid;
        this.userid = userid;
        this.cname = cname;
        this.cphone = cphone;
        this.caddress = caddress;
        this.state = state;
    }
}