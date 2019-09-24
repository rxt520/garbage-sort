package com.smxy.rxt.sys.service;

import com.smxy.rxt.common.service.AbstractService;
import com.smxy.rxt.sys.mapper.SortManMapper;
import com.smxy.rxt.sys.mapper.UserMapper;
import com.smxy.rxt.sys.model.SortMan;
import com.smxy.rxt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private SortManMapper sortManMapper;

    public Integer Regist(User user){ return userMapper.addUser(user); }
    public User login(User user){ return userMapper.login(user); }
    public void toBeSortMan(SortMan sortMan){
        userMapper.toEditUserType(sortMan.getUserid());
        sortManMapper.toAddSortMan(sortMan);
    }

    public Integer resetPassword(String username,String phone,String password){
        return userMapper.resetPassword(username,phone,password);
    }
}
