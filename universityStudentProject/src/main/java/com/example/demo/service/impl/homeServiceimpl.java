package com.example.demo.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.example.demo.dto.homeDto;
import com.example.demo.entity.browsinghistory;
import com.example.demo.entity.homepage;
import com.example.demo.entity.publicationtype;
import com.example.demo.mapper.homeMapper;
import com.example.demo.service.homeService;
import com.example.demo.utils.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class homeServiceimpl implements homeService {

    @Autowired
    private homeMapper homeMapper;

    @Override
    public List<homepage> listHome(homeDto homeDto) {
        List<homepage> homepages = new ArrayList<>();
        if (homeDto.getStatus().equals("1")){
// 当前状态为推荐
// 分析用户帖子类型
            List<homepage> browsinghistoryList = homeMapper.queryBrowseRecordingByuserName(homeDto.getUserName());
            if (CollectionUtil.isNotEmpty(browsinghistoryList)){
                for (homepage homepage1 : browsinghistoryList) {
                    List<homepage> homepages1 = homeMapper.queryByPubType(Arrays.asList(homepage1.getPubType()));
//                    // 生成随机数
//                    Random random = new Random();
//                    random.nextInt(browsinghistoryList.size());
//
//                    //随机插入两三条不同类型的数据
//                    List<homepage> notPubTypeList = homeMapper.queryBrowseRecordingByuserName(homeDto.getUserName()).stream().filter(new Predicate<homepage>() {
//                        @Override
//                        public boolean test(homepage person) {
//                            return !homepage1.getPubType().equals(person.getPubType());//只保留其他类型
//                        }
//                    }).collect(Collectors.toList());
//                    if (CollectionUtil.isNotEmpty(notPubTypeList)){
//                        List<homepage> homepages2 = homeMapper.queryByPubType(notPubTypeList.stream().map(homepage -> homepage.getPubType()).collect(Collectors.toList()));
//
//                    }
                    homepages.addAll(homepages1);
                }
                // 从浏览记录列表中提取所有的帖子类型，并且使用 stream API 将其转化为一个字符串列表
                List<String> collect = browsinghistoryList.stream().map(homepage::getPubType).collect(Collectors.toList());

                // 根据用户还未浏览过的帖子类型查询对应的帖子，并将结果添加到 homepages 列表中
                homepages.addAll(homeMapper.queryByNotType(collect));

                // 查询系统中的全部帖子类别
                List<publicationtype> typeList = homeMapper.queryPublicationType();

                // 遍历所有的帖子类别
                for (publicationtype publicationtype : typeList) {

                    // 获取当前类型帖子在 homepages 列表中的数量（size3）
                    int size3 = homepages.stream().filter(homepage -> homepage.getPubType().equals(publicationtype.getTypeName())).collect(Collectors.toList()).size();

                    // 获取 homepages 列表中除当前类型外的其他类型帖子数量（size2）
                    int size2 = homepages.stream().filter(homepage -> !homepage.getPubType().equals(publicationtype.getTypeName())).collect(Collectors.toList()).size();

                    // 如果除当前类型外的其他类型帖子数量不为 0
                    if (size2 != 0) {
                        // 创建一个随机种子
                        Random random = new Random();

                        // 在当前类型帖子数量范围内生成一个随机数（i2）
                        int i2 = random.nextInt(size3);

                        // 根据随机数（i2）获取对应索引的帖子
                        homepage homepage2 = homepages.get(i2);

                        // 在当前类型帖子数量范围内产生一个随机数（i3），并将其与其他类型帖子的数量相加
                        int i3 = random.nextInt(size3) + size2;

                        // 将随机获取的帖子插入到 homepages 列表中，位置为当前类型帖子数量（size3）
                        homepages.add(size3, homepage2);
                    }
                }

            } else {
                // 如果用户没有浏览记录，则查询所有推荐的帖子并返回
                homepages.addAll(homeMapper.queryHome(homeDto.getStatus()));
            }


        }else if(homeDto.getStatus().equals("2")){
//            热榜
//            readCount
            homepages = homeMapper.queryHomeByOrderByReadCount(homeDto.getStatus());
        }else{
            // 其他状态
            homepages = homeMapper.queryHome(homeDto.getStatus());
        }
        return homepages;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt(8) + 6);
    }

    @Override
    public void publish(homepage homepage) {
        //寫入數據庫
        homeMapper.insertHome(homepage);
    }

    @Override
    public void addBrowseRecording(browsinghistory browsinghistory) {
        List<browsinghistory> browsinghistoryList = homeMapper.queryBrowseRecording(browsinghistory);
        //增加阅读数
        homeMapper.updateReadCount(browsinghistory.getBrowsingHomeId());
        if (CollectionUtil.isEmpty(browsinghistoryList)){
            homeMapper.insertBrowsinghistory(browsinghistory);
        }else {
            homeMapper.updateBrowsinghistory(browsinghistory);
        }
    }

    @Override
    public String uploadImg(MultipartFile homepage) {
        try {
            return Upload.upload(homepage);
        }catch (Exception e){
        }
        return null;
    }

    @Override
    public List<homepage> selectHomePage(String userName) {
        List<Long> homeIds = homeMapper.queryBrowsinghistoryByName(userName);
        if (CollectionUtil.isNotEmpty(homeIds)){
            return homeMapper.queryHomeByIds(homeIds);
        }

        return null;
    }
}
