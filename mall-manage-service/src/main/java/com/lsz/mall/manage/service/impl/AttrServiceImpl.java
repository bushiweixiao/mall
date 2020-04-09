package com.lsz.mall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lsz.mall.bean.PmsBaseAttrInfo;
import com.lsz.mall.bean.PmsBaseAttrValue;
import com.lsz.mall.bean.PmsBaseSaleAttr;
import com.lsz.mall.manage.mapper.PmsBaseAttrInfoMapper;
import com.lsz.mall.manage.mapper.PmsBaseAttrValueMapper;
import com.lsz.mall.manage.mapper.PmsBaseSaleAttrMapper;
import com.lsz.mall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;
    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo1=new PmsBaseAttrInfo();
        pmsBaseAttrInfo1.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos=pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo1);
        for (PmsBaseAttrInfo pmsBaseAttrInfo : pmsBaseAttrInfos) {
            List<PmsBaseAttrValue> pmsBaseAttrValues=new ArrayList<>();
            PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
            String id=pmsBaseAttrInfo.getId();
            pmsBaseAttrValue.setAttrId(id);
            pmsBaseAttrValues=pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return pmsBaseAttrInfos;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        String id=pmsBaseAttrInfo.getId();
        if(StringUtils.isBlank(id)){
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);
            List<PmsBaseAttrValue> attrValueList=pmsBaseAttrInfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
            return "success";
        }
        else{
            //这里有问题，修改属性名称的时候
//            Example example=new Example(PmsBaseAttrInfo.class);
//            example.createCriteria().andEqualTo(id,pmsBaseAttrInfo.getId());
//            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);

           List<PmsBaseAttrValue> attrValueList=pmsBaseAttrInfo.getAttrValueList();
           PmsBaseAttrValue pmsBaseAttrValueDel=new PmsBaseAttrValue();
           pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
           pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);

            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
            return "success";

        }


    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
       return pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }
}
