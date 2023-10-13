package com.hqyj.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePageMergePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hqyj.pojo.UserInfo;
import com.hqyj.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController //restful风格数据接口
@RequestMapping("/user")//映射地址
public class UserInfoController {

    //注入userInfoservice对象
    @Autowired
    UserInfoService userInfoService;
    //注入阿里支付服务类对象
    @Autowired
    AlipayClient alipayClient;

    //定义登录
    @RequestMapping("/login")
    public HashMap<String,Object> login(UserInfo userInfo , HttpSession httpSession){
        //调用userInfoService登录方法
        return userInfoService.login(userInfo,httpSession);
    }

    @RequestMapping("/register")
    public HashMap<String,Object> register(UserInfo userInfo){
        return userInfoService.register(userInfo);
    }

    @RequestMapping("/update")
    public HashMap<String,Object> update(UserInfo userInfo,HttpSession httpSession){
        return userInfoService.update(userInfo);
    }
    @RequestMapping("/logout")
    public HashMap<String,Object> logout(Integer Id,HttpSession httpSession){
        return userInfoService.logout(Id,httpSession);
    }
    @RequestMapping("/select")
    public HashMap<String,Object> select(UserInfo userInfo,HttpSession httpSession){
        return userInfoService.select(userInfo,httpSession);
    }

    @PostMapping("/modifyPwd")
    public HashMap<String,Object> modifyPwd(@RequestBody UserInfo userInfo){
        String userName = userInfo.getUserName();
        String userPwd = userInfo.getUserPwd();
        System.out.println("userName: " + userName);
        System.out.println("userPwd: " + userPwd);
        return userInfoService.modifyPwd(userName, userPwd);
    }

    @RequestMapping("/pay")
    public HashMap<String,Object> pay(String num,String name,Double money,String body) throws JsonProcessingException, AlipayApiException {
        HashMap<String,Object> map = new HashMap<String, Object>();
        //请求支付宝服务器参数
        HashMap<String,Object> params = new HashMap<String, Object>();
        //订单编号
        params.put("out_trade_no",num);
        //名称
        params.put("subject",name);
        //金额
        params.put("total_amount",money);
        //备注
        params.put("body",body);
        //请求参数转换为JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(params);
        //支付宝请求对象
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        //设置请求参数
        request.setBizContent(jsonString);
        //发送请求获取响应结果对象
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        //通过响应结果判断是否成功
        if (response.isSuccess()){
            map.put("code",200);
            //存储链接地址
            map.put("url",response.getQrCode());
        }else {
            map.put("code",500);
            map.put("msg","支付失败");
        }


        return map;
    }

}
