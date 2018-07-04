package com.zzg.o2o.util;

/**
 * 路径处理工具类
 */

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/JavaWorkSpace/web_img/";
        }else {
            basePath = "/home/tmp/image/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImagePath(long shopId){
        String imageUpload = "/upload/item/shop/" + shopId + "/";
        return imageUpload.replace("/", seperator);
    }

}
