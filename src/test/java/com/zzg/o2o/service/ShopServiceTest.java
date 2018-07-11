package com.zzg.o2o.service;

import com.zzg.o2o.BaseTest;
import com.zzg.o2o.dto.ShopExecution;
import com.zzg.o2o.entity.Area;
import com.zzg.o2o.entity.PersonInfo;
import com.zzg.o2o.entity.Shop;
import com.zzg.o2o.entity.ShopCategory;
import com.zzg.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1l);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1l);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺2");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setAdvice("审核中");
        File imgFile = new File("F:\\test.jpg");
        ShopExecution se = shopService.addShop(shop, imgFile);
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());

    }

}
