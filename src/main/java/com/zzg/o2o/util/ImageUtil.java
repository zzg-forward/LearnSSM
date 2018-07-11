package com.zzg.o2o.util;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
//Spring图像流
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 图片处理工具类
 */
public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    public static String generateThumbnail(File thumbnail, String targetAddr){
        String realFileName = getRandomFileName();  //随机文件名
        String extension = getFileExtension(thumbnail);  //文件扩展名
        makeDirPath(targetAddr);  //创建文件夹
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail).size(200, 200).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 随机文件名：日期 + 随机5位数
     * @return
     */
    private static String getRandomFileName(){
        int num = r.nextInt(89999) + 10000;
        String dateStr = dateFormat.format(new Date());
        return dateStr + num;
    }

    /**
     * 获取文件扩展名
     * @param cFile
     * @return
     */
    private static String getFileExtension(File cFile){
        String originalFileName = cFile.getName();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 创建文件夹
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr){
        String filePath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(filePath);
        if (!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    //for test
    public static void main(String[] args){

    }


}
