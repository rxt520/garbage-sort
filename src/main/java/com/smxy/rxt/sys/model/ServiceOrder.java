package com.smxy.rxt.sys.model;

import javax.persistence.*;

@Table(name = "service")
public class ServiceOrder {
    @Id
    @Column(name = "serviceId")
    private Integer serviceid;

    @Column(name = "userId")
    private Integer userid;

    @Column(name = "sortId")
    private Integer sortid;

    @Column(name = "Expire")
    private String expire;

    /**
     * @return serviceId
     */
    public Integer getServiceid() {
        return serviceid;
    }

    /**
     * @param serviceid
     */
    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
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
     * @return Expire
     */
    public String getExpire() {
        return expire;
    }

    /**
     * @param expire
     */
    public void setExpire(String expire) {
        this.expire = expire;
    }
}