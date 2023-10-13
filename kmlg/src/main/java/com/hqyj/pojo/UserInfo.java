package com.hqyj.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfo {

    //用户id
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    //用户名
    @TableField(value = "user_name")
    private String userName;
    //密码
    private String userPwd;
    //邮箱
    private String userEmail;
    //性别
    private String userGender;
    //生日
    private String userBirth;
    //个人简介
    private String userResume;
}
