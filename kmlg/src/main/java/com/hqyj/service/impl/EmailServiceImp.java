package com.hqyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hqyj.dao.UserInfoDao;
import com.hqyj.pojo.UserInfo;
import com.hqyj.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

@Service
public class EmailServiceImp implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Resource
    private UserInfoDao userInfoDao;

    //application.properties中已配置的值
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public HashMap<String, Object> sendMimeMail(String email, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            // 邮箱格式验证
            if (!isValidQQEmail(email)) {
                map.put("code", 500);
                map.put("msg", "邮箱格式不正确");
                return map;
            }
            //邮箱信息
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            //主题
            mailMessage.setSubject("用户验证");
            //生成随机数
            String code = randomCode();
            System.out.println("code = " + code);
            //将随机数放置到session中
            session.setAttribute("email",email);
            session.setAttribute("code",code);
            mailMessage.setText("验证码是: "+ code);
            mailMessage.setTo(email);
            //自己的邮箱
            mailMessage.setFrom(from);
            mailSender.send(mailMessage);
            map.put("code",200);
        }catch (Exception e) {
            e.printStackTrace();
            map.put("code",500);
        }
        return map;
    }

    @Override
    public String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    @Override
    public HashMap<String, Object> emaillogin(String voCode,HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        //获取session中的验证信息
        String email = (String) session.getAttribute("email");
        String code = (String) session.getAttribute("code");
        QueryWrapper<UserInfo> query = new QueryWrapper<>();
        //定义用户名查询条件
        query.eq("user_email",email);
        //查询结果
        UserInfo user1 = userInfoDao.selectOne(query);
        session.setAttribute("用户名",user1.getUserId());

        //如果email数据为空，或者不一致，注册失败
        if (email == null || email.isEmpty()) {
            map.put("code",500);
            map.put("msg","请输入邮箱");
            return map;
        } else if (!voCode.equals(code)) {
            map.put("code",500);
            map.put("msg","验证码错误");
            return map;
        }
        // 登录成功，根据邮箱查询数据库是否存在
        UserInfo user = userInfoDao.getUserByEmail(email);
        if (user == null) {
            map.put("code", 500);

            map.put("msg", "邮箱不存在");
            return map;
        }
        //登录成功去掉验证码
        session.removeAttribute("code");

        map.put("code",200);
        return map;
    }

    @Override
    public HashMap<String, Object> setPwd(String password, HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String email = (String) session.getAttribute("email");
        System.out.println("密码："+password);
        System.out.println("邮箱："+email);
        try{
            userInfoDao.updatePasswordByEmail(email, password);
        }catch (Exception e){
            map.put("code",500);
            map.put("msg","更改失败");
        }
        map.put("code", 200);
        map.put("msg", "密码设置成功");
        return map;
    }

    @Override
    public HashMap<String, Object> loginout(HttpSession session) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        session.invalidate();//清空session
        map.put("code",200);
        return map;
    }

    @Override
    public boolean isValidQQEmail(String email) {
        // QQ邮箱的正则表达式
        String regex = "[1-9]\\d{4,10}@qq\\.com";
        return email.matches(regex);
    }
}
