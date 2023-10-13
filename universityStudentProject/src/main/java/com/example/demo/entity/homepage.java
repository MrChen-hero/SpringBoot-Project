package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class homepage {
    private Long id;
    private String title;
    private String content;
    private String pubType;
    private String fileList;
    private String imgUrl;
    private String createTime;
    private String userName;
    //提醒誰看
    private String remind;
    //誰可以看
    private String accessible;

    private Integer readCount;
    private Integer likeCount;
    private Integer collectCount;
    public String getPubType(){
        return pubType;
    }
}
