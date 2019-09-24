package com.smxy.rxt.sys.model;

import java.math.BigDecimal;
import javax.persistence.*;

public class SortMan {
    @Id
    @Column(name = "sortId")
    private Integer sortid;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "serviceCount")
    private Integer servicecount;

    private BigDecimal wage;

    private BigDecimal score;

    private Integer age;

    @Column(name = "IDcard")
    private String idcard;

    @Column(name = "native__")
    private String native_;

    private String nation;

    @Column(name = "currentCity")
    private String currentcity;

    private String state;

    private String picture;

    private String introduce;

    /**
     * @return sortId
     */
    public Integer getSortid() {
        return sortid;
    }

    /**
     * @param sortid
     */
    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    /**
     * @return userId
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
     * @return serviceCount
     */
    public Integer getServicecount() {
        return servicecount;
    }

    /**
     * @param servicecount
     */
    public void setServicecount(Integer servicecount) {
        this.servicecount = servicecount;
    }

    /**
     * @return wage
     */
    public BigDecimal getWage() {
        return wage;
    }

    /**
     * @param wage
     */
    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    /**
     * @return score
     */
    public BigDecimal getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return IDcard
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * @param idcard
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    /**
     * @return native__
     */
    public String getnative_() {
        return native_;
    }

    /**
     * @param native_
     */
    public void setnative_(String native_) {
        this.native_ = native_;
    }

    /**
     * @return nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * @param nation
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * @return currentCity
     */
    public String getCurrentcity() {
        return currentcity;
    }

    /**
     * @param currentcity
     */
    public void setCurrentcity(String currentcity) {
        this.currentcity = currentcity;
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
     * @return picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return introduce
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public SortMan(Integer userid, Integer servicecount, BigDecimal wage, BigDecimal score, Integer age, String idcard, String native_, String nation, String currentcity, String state, String picture, String introduce) {
        this.userid = userid;
        this.servicecount = servicecount;
        this.wage = wage;
        this.score = score;
        this.age = age;
        this.idcard = idcard;
        this.native_ = native_;
        this.nation = nation;
        this.currentcity = currentcity;
        this.state = state;
        this.picture = picture;
        this.introduce = introduce;
    }
}