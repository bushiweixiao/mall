package com.lsz.mall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lsz.mall.bean.PmsBaseCatalog3;
import com.lsz.mall.bean.PmsBaseCatalog2;
import com.lsz.mall.service.CataLogService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lsz.mall.bean.PmsBaseCatalog1;

import java.util.List;


@Controller
@CrossOrigin
public class CataLogController {

    @Reference
    CataLogService cataLogService;
    @ResponseBody
    @RequestMapping("index")
    public String index(){
        return "hello world";
    }

    @ResponseBody
    @RequestMapping("getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){

        List<PmsBaseCatalog1> pmsBaseCatalog1s=cataLogService.getCataLog1();
        return pmsBaseCatalog1s;
    }

    @ResponseBody
    @RequestMapping("getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(@RequestParam String catalog1Id){
        List<PmsBaseCatalog2> pmsBaseCatalog2s=cataLogService.getCataLog2(catalog1Id);
        return pmsBaseCatalog2s;
    }

    @ResponseBody
    @RequestMapping("getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(@RequestParam String catalog2Id){
        List<PmsBaseCatalog3> pmsBaseCatalog3s=cataLogService.getCataLog3(catalog2Id);
        return pmsBaseCatalog3s;
    }

}
