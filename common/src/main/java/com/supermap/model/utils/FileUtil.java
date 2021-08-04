package com.supermap.model.utils;


import cn.hutool.core.util.RandomUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * 文件操作工具类
 * @author : zhangfx 2019/10/8/ 15:14
 * @version : 1.0
 */
public class FileUtil {

    /**
     * 网络上传文件转为本地文件
     * @param file 文件
     * @param path 路径
     * @return 本地文件
     * @throws IOException 异常
     */
    public static File multipartFileToFile(MultipartFile file,String path) throws IOException {
        //1.存放文件到指定位置
        path = path.replaceAll("\\\\", "/");
        //判断文件夹是否存在
        if(!new File(path).exists()){
            //创建文件夹
            cn.hutool.core.io.FileUtil.mkdir(path);
        }
        //获取上传文件名,包含后缀
        String originalFilename = file.getOriginalFilename();
        path = path+ RandomUtil.randomString(5)+originalFilename;
        File dest = new File(path);
        //1.存放到指定目录
        file.transferTo(dest);
        return dest;
    }

}
