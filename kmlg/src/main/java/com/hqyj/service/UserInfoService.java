package com.hqyj.service;

import com.hqyj.pojo.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface UserInfoService {

    //登录的业务
    HashMap<String,Object> login(UserInfo userInfo , HttpSession httpSession);
    //注册业务
    HashMap<String,Object> register(UserInfo userInfo);
    //编辑个人信息
    HashMap<String,Object> update(UserInfo userInfo);
    //注销账号
    HashMap<String,Object> logout(Integer Id,HttpSession httpSession);
    //查询
    HashMap<String,Object> select(UserInfo userInfo, HttpSession httpSession);

    HashMap<String,Object> modifyPwd(String userName,String password);

}
