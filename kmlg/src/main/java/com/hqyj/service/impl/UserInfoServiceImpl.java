package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.hqyj.dao.UserInfoDao;
import com.hqyj.pojo.UserInfo;
import com.hqyj.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    //注入userInfoDao对象
    @Resource
    UserInfoDao userInfoDao;

    @Override
    public HashMap<String, Object> login(UserInfo userInfo , HttpSession httpSession) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        //1 验证用户名是否正确
        //创建查询对象
        QueryWrapper<UserInfo> query = new QueryWrapper<>();
        //定义用户名查询条件
        query.eq("user_name",userInfo.getUserName());
        //查询结果
        UserInfo user = userInfoDao.selectOne(query);
        if(user != null){
            //定义密码查询条件
            query.eq("user_pwd",userInfo.getUserPwd());
            //查询结果
            UserInfo user1 = userInfoDao.selectOne(query);
            if(user1 != null){
                map.put("code",200);
                httpSession.setAttribute("用户名",user1.getUserId());
            }else{
                map.put("code",500);
                map.put("msg","密码不正确");
            }
        }else{
            //200:成功；500:错误
            map.put("code",500);
            map.put("msg","用户名不正确");
        }


        return map;
    }

    @Override
    public HashMap<String, Object> register(UserInfo userInfo) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        int user = userInfoDao.insert(userInfo);
        if(user > 0){
            map.put("code",200);
            map.put("msg","注册成功");
        }else {
            map.put("code",500);
            map.put("msg","注册失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> update(UserInfo userInfo) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        int num = userInfoDao.updateById(userInfo);
        if(num > 0){
            map.put("code",200);
            map.put("msg","修改成功");
        }else {
            map.put("code",500);
            map.put("msg","修改失败");
        }

        return map;
    }

    @Override
    public HashMap<String, Object> logout(Integer Id,HttpSession httpSession) {
        HashMap<String,Object> map = new HashMap<String, Object>();

        int num = userInfoDao.deleteById(Id);
        if(num > 0){
            map.put("code",200);
            map.put("msg","删除成功");
        }else {
            map.put("code",500);
            map.put("msg","删除失败");
        }

        return map;
    }

    @Override
    public HashMap<String, Object> select(UserInfo userInfo,HttpSession httpSession) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        //创建查询对象
        QueryWrapper<UserInfo> query = new QueryWrapper<>();
        query.eq("user_id",httpSession.getAttribute("用户名"));


        //调用查询方法
        UserInfo user = userInfoDao.selectOne(query);
//        List list = userInfoDao.selectList(query);
        if(user != null){
            map.put("code",200);
            map.put("data",user);
        }else{
            map.put("code",500);
            map.put("msg","没有查询到数据");
        }
//
//        if(list.size()>0){
//            map.put("code",200);
//            map.put("data",list);
//        }else{
//            map.put("code",500);
//            map.put("msg","没有查询到数据");
//        }
        return map;
    }

    @Override
    public HashMap<String,Object> modifyPwd(String userName,String userPwd){
        HashMap<String,Object> map = new HashMap<String, Object>();
        // 创建查询条件对象
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);

        // 查询用户信息
        UserInfo user = userInfoDao.selectOne(queryWrapper);

        if (user != null) {
            // 用户名存在，更新密码
            user.setUserPwd(userPwd);
            int rowsAffected = userInfoDao.updateById(user);

            if (rowsAffected > 0) {
                // 密码更新成功
                map.put("code", 200);
                map.put("msg", "密码更新成功");
            } else {
                // 密码更新失败
                map.put("code", 500);
                map.put("msg", "密码更新失败");
            }
        } else {
            // 用户名不存在
            map.put("code", 500);
            map.put("msg", "用户名不存在");
        }
        return map;
    }

}
