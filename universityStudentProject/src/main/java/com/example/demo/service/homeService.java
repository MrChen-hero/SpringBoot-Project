package com.example.demo.service;

import com.example.demo.dto.homeDto;
import com.example.demo.entity.browsinghistory;
import com.example.demo.entity.homepage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface homeService {
    /**
     * 文章列表
     * @return
     */
    List<homepage> listHome(homeDto homeDto);

    /**
     * 發佈接口
     * @param homepage
     */
    void publish(homepage homepage);

    void addBrowseRecording(browsinghistory browsinghistory);

    String uploadImg(MultipartFile homepage);

    List<homepage> selectHomePage(String userName)
            ;
}
