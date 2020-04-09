package com.lsz.mall.service;

import com.lsz.mall.bean.PmsSkuInfo;

import java.math.BigDecimal;
import java.util.List;

public interface SkuService {
    List<PmsSkuInfo> getSkuListBySpuId(String spuId);

    void saveSku(PmsSkuInfo skuInfo);

    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> getSkuListByCatalog3Id(String catalog3Id);

   boolean checkPrice(BigDecimal skuPrice, String skuId);
}
