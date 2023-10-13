package com.hqyj.controller;

import com.hqyj.dao.PayInfoDao;
import com.hqyj.pojo.PayInfo;
import com.hqyj.service.PayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController //restful风格数据接口
@RequestMapping("/pay")//映射地址
public class PayInfoController {
    @Autowired
    PayInfoService payInfoService;

    @RequestMapping("/add")
    public HashMap<String,Object> add(PayInfo payInfo){
        return payInfoService.add(payInfo);
    }
    //修改
    @RequestMapping("/update")
    public HashMap<String,Object> update(PayInfo payInfo){
        return payInfoService.update(payInfo);
    }
    //删除
    @RequestMapping("/del")
    public HashMap<String,Object> del(Integer Id){
        return payInfoService.del(Id);
    }
}

