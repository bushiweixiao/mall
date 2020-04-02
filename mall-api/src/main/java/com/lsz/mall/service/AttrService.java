package com.lsz.mall.service;

import com.lsz.mall.bean.PmsBaseAttrInfo;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);
}
