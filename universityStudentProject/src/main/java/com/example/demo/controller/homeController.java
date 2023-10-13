package com.example.demo.controller;

import com.example.demo.dto.homeDto;
import com.example.demo.entity.browsinghistory;
import com.example.demo.entity.homepage;
import com.example.demo.service.homeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//跨域问题
@CrossOrigin
@RestController
@RequestMapping("/home")
public class homeController {

    @Autowired
    private homeService homeService;

    /**
     * 列表接口
     * @return
     */
    @RequestMapping("/list")
    public List<homepage> getHomeList(homeDto homeDto){
       return homeService.listHome(homeDto);
    }


    /**
     * 發佈帖子接口
     * @param request
     * @param homepage
     */
    @PostMapping("/uploadHome")
    @ResponseBody
    public void uploadHomeList(@RequestBody homepage homepage){
        homeService.publish(homepage);
    }

    /**
     * 图片上传
     * @param request
     * @param homepage
     */
    @PostMapping(value = "/uploadImg",headers = "content-type=multipart/form-data")
    @ResponseBody
    public String uploadHomeList(MultipartFile file){
        return homeService.uploadImg(file);
    }

    /**
     * 瀏覽記錄
     * @param request
     * @param homepage
     */
    @RequestMapping(value = "/browseRecording",method = RequestMethod.GET)
    public void browseRecording(browsinghistory browsinghistory){
        homeService.addBrowseRecording(browsinghistory);
    }

    @RequestMapping("/queryBecordingHomeById/{userName}")
    public List<homepage> queryBecordingHomeById(@PathVariable("userName") String userName){
        return homeService.selectHomePage(userName);
    }
}
