package com.lsz.mall.service;

import com.lsz.mall.bean.PmsBaseCatalog1;
import com.lsz.mall.bean.PmsBaseCatalog2;
import com.lsz.mall.bean.PmsBaseCatalog3;

import java.util.List;

public interface CataLogService {

    List<PmsBaseCatalog1> getCataLog1();

    List<PmsBaseCatalog2> getCataLog2(String catalog1Id);

    List<PmsBaseCatalog3> getCataLog3(String catalog2Id);
}
