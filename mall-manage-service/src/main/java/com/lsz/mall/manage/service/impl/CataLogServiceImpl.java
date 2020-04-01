package com.lsz.mall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lsz.mall.bean.PmsBaseCatalog1;
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
    PmsBaseCatalog3Mapper pmsBaseCatalog4Mapper;
    @Override
    public List<PmsBaseCatalog1> getCataLog1() {
        return pmsBaseCatalog1Mapper.selectAll();
    }
}
