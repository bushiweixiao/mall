package com.lsz.mall.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {
    @RequestMapping("index")
    public String index(ModelMap map) {
        map.put("hello", "hello,thymeleaf");
        return "index";
    }
}
