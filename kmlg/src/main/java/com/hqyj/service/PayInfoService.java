package com.hqyj.service;

import com.hqyj.pojo.PayInfo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface PayInfoService {

    HashMap<String,Object> add(PayInfo payInfo);
    //注册业务
    HashMap<String,Object> update(PayInfo payInfo);
    //编辑个人信息
    HashMap<String,Object> del(Integer Id);
    //注销账号
}
