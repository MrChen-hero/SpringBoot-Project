package com.hqyj.service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface EmailService {
    /**
     * 给邮箱发送验证码
     * @param email
     * @param session
     * @return
     */
    HashMap<String, Object> sendMimeMail(String email, HttpSession session);

    /**
     * 随机生成6位数的验证码
     *
     * @return String code
     */
    String randomCode();

    /**
     * 邮箱登录
     * @param voCode
     * @param session
     * @return
     */
    HashMap<String, Object> emaillogin(String voCode, HttpSession session);
    /**
     * 忘记密码
     * @param password
     * @param session
     * @return
     */
    HashMap<String, Object> setPwd(String password, HttpSession session);

    /**
     * 退出系统
     * @param session
     * @return
     */
    HashMap<String, Object> loginout(HttpSession session);

    /**
     * 验证QQ邮箱
     * @param email
     * @return
     */
    boolean isValidQQEmail(String email);
}
