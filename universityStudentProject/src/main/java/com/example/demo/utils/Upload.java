package com.example.demo.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;


/**
 * 上傳方法
 */
@Component
public class Upload {
    //存儲圖片的地址 本地地址可以用这个
    private static final String address = "C:\\Users\\Mr_Chen\\Desktop\\Java\\Xblog-server(1)\\universityStudentProject\\src\\main\\resources\\files\\";


    public static String upload(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();//获取文件的名称

        /*
        我们需要给我们的文件加一个文件前缀，不可以加文件后缀，因为我们的文件还有后缀名
        加文件前缀的目的是为了防止文件重名，文件重名的话后续的重名文件会覆盖掉前面的文件
         */
        String flag = IdUtil.fastSimpleUUID();//通过Hutool工具包的IdUtil类获取uuid作为前缀
        String rootFilePath = address + flag + "_" + filename;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);//使用Hutool工具包将我们接收到文件保存到rootFilePath中
        System.out.println();
        return rootFilePath;//返回结果   ：  url，
    }
}
