package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hqyj.dao.PayInfoDao;
import com.hqyj.pojo.PayInfo;
import com.hqyj.pojo.UserInfo;
import com.hqyj.service.PayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class PayInfoServiceImpl implements PayInfoService {
    @Resource
    PayInfoDao payInfoDao;

    @Override
    public HashMap<String, Object> add(PayInfo payInfo) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        int num = payInfoDao.insert(payInfo);
        //num > 0表示成功
        if(num > 0){
            map.put("code",200);
            map.put("msg","添加成功");
        }else{
            map.put("code",500);
            map.put("msg","添加失败");
        }
        return map;
    }

    @Override
    public HashMap<String, Object> update(PayInfo payInfo) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        //创建查询对象
        QueryWrapper<PayInfo> query = new QueryWrapper<>();
        //定义查询条件列名
        boolean isNull = payInfo.getPayId() != null;
        query.eq(isNull,"pay_id",payInfo.getPayId());
        isNull = payInfo.getPayType() != null && !payInfo.getPayType().equals("");
        query.eq(isNull,"pay_type",payInfo.getPayType());
        //调用查询方法
        List<PayInfo> list = payInfoDao.selectList(query);

        if(list.size()>0){
            map.put("code",200);
            map.put("data",list);
        }else{
            map.put("code",500);
            map.put("msg","没有查询到数据");
        }

        return map;
    }

    @Override
    public HashMap<String, Object> del(Integer Id) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        int num = payInfoDao.deleteById(Id);
        //num > 0表示成功
        if(num > 0){
            map.put("code",200);
            map.put("msg","删除成功");
        }else{
            map.put("code",500);
            map.put("msg","删除失败");
        }
        return map;
    }
}
