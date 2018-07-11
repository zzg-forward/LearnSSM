package com.zzg.o2o.service;

import com.zzg.o2o.dto.ShopExecution;
import com.zzg.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
