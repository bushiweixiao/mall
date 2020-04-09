package com.lsz.mall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lsz.mall.bean.*;
import com.lsz.mall.manage.mapper.*;
import com.lsz.mall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements SpuService {
    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;
    @Autowired
    PmsProductImageMapper pmsProductImageMapper;
    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;
    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo=new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return pmsProductInfoMapper.select(pmsProductInfo);
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {
        //保存SpuInfo
        pmsProductInfoMapper.insertSelective(pmsProductInfo);
        String spuId = pmsProductInfo.getId();
        //保存SPU图片信息
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        for (PmsProductImage pmsProductImage :
                spuImageList) {
            pmsProductImage.setProductId(spuId);
            pmsProductImageMapper.insert(pmsProductImage);
        }
        //保存SPU销售属性
        PmsProductSaleAttr spuSaleAttr = new PmsProductSaleAttr();
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        for (PmsProductSaleAttr saleAttr :
                spuSaleAttrList) {
            saleAttr.setProductId(spuId);

            pmsProductSaleAttrMapper.insertSelective(saleAttr);
            //保存SPU销售属性值
            List<PmsProductSaleAttrValue> spuSaleAttrValueList = saleAttr.getSpuSaleAttrValueList();
            for (PmsProductSaleAttrValue attrValue :
                    spuSaleAttrValueList) {
                attrValue.setProductId(spuId);
                attrValue.setSaleAttrId( saleAttr.getId());
                pmsProductSaleAttrValueMapper.insert(attrValue);
            }
        }
        return "success";
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        PmsProductSaleAttr pmsProductSaleAttr1=new PmsProductSaleAttr();
        pmsProductSaleAttr1.setId(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs=pmsProductSaleAttrMapper.select(pmsProductSaleAttr1);

        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrs) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue=new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getSaleAttrId());
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues=pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        }
        return pmsProductSaleAttrs;
    }

    @Override
    public List<PmsProductImage> spuImageList(String spuId) {
        PmsProductImage pmsProductImage=new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        return pmsProductImageMapper.select(pmsProductImage);
    }


}
