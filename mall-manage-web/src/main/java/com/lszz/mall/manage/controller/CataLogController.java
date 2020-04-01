package com.lszz.mall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lsz.mall.service.CataLogService;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lsz.mall.bean.PmsBaseCatalog1;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@CrossOrigin
public class CataLogController {

    @Reference
    CataLogService cataLogService;
    @ResponseBody
    @RequestMapping("getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){

        List<PmsBaseCatalog1> pmsBaseCatalog1s=cataLogService.getCataLog1();
        return pmsBaseCatalog1s;
    }
}
