package com.lsz.mall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lsz.mall.bean.*;
import com.lsz.mall.manage.util.PmsUploadUtil;
import com.lsz.mall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {
    @Reference
    SpuService spuService;

    @ResponseBody
    @RequestMapping("spuImageList")
    public List<PmsProductImage> spuImageList(String spuId) {
        return spuService.spuImageList(spuId);
    }


    @ResponseBody
    @RequestMapping("spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        return spuService.spuSaleAttrList(spuId);
    }

    //上传图片至fastdfs并返回url
    @ResponseBody
    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        return PmsUploadUtil.uploadImage(file);
    }


    @ResponseBody
    @RequestMapping("spuList")
    public List<PmsProductInfo> spuList(String catalog3Id) {
        return spuService.spuList(catalog3Id);
    }


    //由编辑SPU页的保存按钮所调用，保存SPU页面信息
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }


}