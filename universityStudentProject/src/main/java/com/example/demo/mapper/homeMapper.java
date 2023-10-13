package com.example.demo.mapper;


import com.example.demo.entity.browsinghistory;
import com.example.demo.entity.homepage;
import com.example.demo.entity.publicationtype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
@Mapper
public interface homeMapper {

    List<homepage> queryHome(@Param("status") String status);

    void insertHome(homepage homepage);

    List<Long> queryReturnByName(String userName);

    void insertReturn(@Param("hisHomeIds") List<Long> hisHomeIds, @Param("userName") String userName);

    void insertBrowsinghistory(browsinghistory browsinghistory);

    List<Long> queryBrowsinghistoryByName(@Param("userName") String userName);

    List<homepage> queryHomeByIds(@Param("homeIds") List<Long> homeIds);

    List<browsinghistory> queryBrowseRecording(browsinghistory browsinghistory);

    List<homepage> queryBrowseRecordingByuserName(@Param("userName") String userName);

    List<homepage> queryByPubType(@Param("pubTypeList") List<String> pubType);

    List<homepage> queryByNotType(@Param("pubTypeList") List<String> pubTypeList);

    void updateBrowsinghistory(browsinghistory browsinghistory);

    List<homepage> queryHomeByOrderByReadCount(@Param("status") String status);

    void updateReadCount(@Param("browsingHomeId") String browsingHomeId);

    List<publicationtype> queryPublicationType();
}
