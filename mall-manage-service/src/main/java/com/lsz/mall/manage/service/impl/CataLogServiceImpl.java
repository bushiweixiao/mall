package com.lsz.mall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lsz.mall.bean.PmsBaseCatalog1;
import com.lsz.mall.bean.PmsBaseCatalog2;
import com.lsz.mall.bean.PmsBaseCatalog3;
import com.lsz.mall.manage.mapper.PmsBaseCatalog1Mapper;
import com.lsz.mall.manage.mapper.PmsBaseCatalog2Mapper;
import com.lsz.mall.manage.mapper.PmsBaseCatalog3Mapper;
import com.lsz.mall.service.CataLogService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class CataLogServiceImpl implements CataLogService {
    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;

    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;

    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;
    @Override
    public List<PmsBaseCatalog1> getCataLog1() {
        return pmsBaseCatalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCataLog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2=new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        return pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);

    }

    @Override
    public List<PmsBaseCatalog3> getCataLog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3=new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        return pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
    }

}
