package com.smxy.rxt.common.model;

public class R {
    private int code;
    private String msg;


    public static R ok() {
        R r = new R();
        r.code = 0; //成功
        r.msg = "请求成功";
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.code = 0; //成功
        r.msg = msg;
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.code = 1;//失败
        r.msg = msg;
        return r;
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.code = code;//失败
        r.msg = msg;
        return r;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
