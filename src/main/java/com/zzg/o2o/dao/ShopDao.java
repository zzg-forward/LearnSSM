package com.zzg.o2o.dao;

import com.zzg.o2o.entity.Shop;

public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);
}
