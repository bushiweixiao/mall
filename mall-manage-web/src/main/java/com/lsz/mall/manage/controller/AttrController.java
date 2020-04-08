package com.lsz.mall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lsz.mall.bean.PmsBaseAttrInfo;
import com.lsz.mall.bean.PmsBaseAttrValue;
import com.lsz.mall.bean.PmsBaseSaleAttr;
import com.lsz.mall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {
    @Reference
    AttrService attrService;

    @ResponseBody
    @RequestMapping("baseSaleAttrList")
    public List<PmsBaseSaleAttr>  baseSaleAttrList(){
        List<PmsBaseSaleAttr> baseSaleAttrs=attrService.baseSaleAttrList();
        return baseSaleAttrs;
    }

    @ResponseBody
    @RequestMapping("attrInfoList")
    public List<PmsBaseAttrInfo>  attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> pmsBaseAttrInfos=attrService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfos;
    }

    @ResponseBody
    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        String statue=attrService.saveAttrInfo(pmsBaseAttrInfo);
        return statue;
    }

    @ResponseBody
    @RequestMapping("getAttrValueList")
    public List<PmsBaseAttrValue>  getAttrValueList(String attrId){
        List<PmsBaseAttrValue> pmsBaseAttrValues=attrService.getAttrValueList(attrId);
        return pmsBaseAttrValues;

    }
}
