package com.lsz.mall.service;

import com.lsz.mall.bean.*;

import java.util.List;
import java.util.Map;

public interface SpuService {
    List<PmsProductInfo> spuList(String catalog3Id);

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

}
