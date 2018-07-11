package com.zzg.o2o.service.impl;

import com.zzg.o2o.dao.ShopDao;
import com.zzg.o2o.dto.ShopExecution;
import com.zzg.o2o.entity.Shop;
import com.zzg.o2o.enums.ShopStateEnum;
import com.zzg.o2o.service.ShopService;
import com.zzg.o2o.util.ImageUtil;
import com.zzg.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired  //自动注入
    private ShopDao shopDao;

    @Override
    @Transactional  //事务
    public ShopExecution addShop(Shop shop, File shopImg) {
        //空值判断
        if (shop == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //初始化店铺信息
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //添加店铺信息
            int effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0){
                throw new RuntimeException("创建店铺失败");
            }else {
                //添加存储图片
                if (shopImg != null){
                    try {
                        addShopImg(shop, shopImg);
                    }catch (Exception e){
                        throw new RuntimeException("addShopImg error:" + e.getMessage());
                    }
                    effectNum = shopDao.updateShop(shop);
                    if (effectNum <= 0){
                        throw new RuntimeException("更新图片地址失败");
                    }
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException("addShop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    //添加店铺图片信息
    private void addShopImg(Shop shop, File shopImg) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgPath = ImageUtil.generateThumbnail(shopImg, dest);
        shop.setShopImg(shopImgPath);
    }
}
