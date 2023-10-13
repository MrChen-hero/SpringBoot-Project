package com.hqyj.controller;

import com.hqyj.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;
    /**
     * 发送注册验证码
     * @param email
     * @param httpSession
     * @return
     */
    @PostMapping("/sendEmail/{email}")
    @ResponseBody
    public HashMap<String, Object> sendEmail(@PathVariable String email, HttpSession httpSession) {
        return emailService.sendMimeMail(email, httpSession);
    }
    @PostMapping("/emailogin")
    public HashMap<String, Object> emailogin(@RequestBody String voCode, HttpSession session){
        return emailService.emaillogin(voCode,session);
    }
    @PostMapping("/setPwd")
    public HashMap<String, Object> setPwd(@RequestBody String password,HttpSession session){
        return emailService.setPwd(password,session);
    }
    @GetMapping("/loginout")
    public HashMap<String, Object>loginout(HttpSession session){
        return emailService.loginout(session);
    }
}
