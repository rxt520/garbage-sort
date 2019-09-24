package com.smxy.rxt.sys.mapper;

import com.smxy.rxt.sys.model.SortMan;
import com.smxy.rxt.sys.model.User;
import tk.mybatis.mapper.common.Mapper;


public interface UserMapper extends Mapper<User> {
    public Integer addUser(User user);
    public User login(User user);
    public Integer toEditUserType(Integer userId);
    public Integer resetPassword(String username,String phone,String password);
}