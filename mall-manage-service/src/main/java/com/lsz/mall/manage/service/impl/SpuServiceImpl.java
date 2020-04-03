package com.lsz.mall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lsz.mall.bean.PmsProductInfo;
import com.lsz.mall.manage.mapper.PmsProductInfoMapper;
import com.lsz.mall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo=new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }
}
