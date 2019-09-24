package com.smxy.rxt.sys.model;

import javax.persistence.*;

@Table(name = "userall")
public class User {
    @Id
    @Column(name = "userId")
    private Integer userid;

    @Column(name = "userName")
    private String username;

    @Column(name = "passWord")
    private String password;

    @Column(name = "userTypeId")
    private Integer usertypeid;

    private String phone;

    private String email;

    private String address;

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
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return passWord
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return userTypeId
     */
    public Integer getUsertypeid() {
        return usertypeid;
    }

    /**
     * @param usertypeid
     */
    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    public User() {
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public User(String username, String password, Integer usertypeid, String phone, String email, String address) {
        this.username = username;
        this.password = password;
        this.usertypeid = usertypeid;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Integer userid, String username, String password, Integer usertypeid, String phone, String email, String address) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.usertypeid = usertypeid;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}