package com.hqyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hqyj.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper //mybatis-plus注解
public interface UserInfoDao extends BaseMapper<UserInfo> {
    void updatePasswordByEmail(@Param("email") String email, @Param("password") String password);
    UserInfo getUserByEmail(String email);

}
