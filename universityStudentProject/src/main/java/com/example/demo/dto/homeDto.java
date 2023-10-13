package com.example.demo.dto;

import lombok.Data;

@Data
public class homeDto {
    private String userName;
    private String status;
    private Integer pageNum;
    private Integer pageSize;
}
