package com.lsz.mall.user.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lsz.mall.bean.UmsMemberReceiveAddress;
import com.lsz.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Reference
    UserService userService;
    @ResponseBody
    @RequestMapping("getReceiveAddressByMemberIdExample")
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberIdExample(String memberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses=userService.getReceiveAddressByMemberIdExmaple(memberId);
        return umsMemberReceiveAddresses;
    }

    @ResponseBody
    @RequestMapping("getReceiveAddressByMemberId")
    public List<UmsMemberReceiveAddress>  getReceiveAddressByMemberId(String memberId){
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses=userService.getReceiveAddressByMemberId(memberId);
        return umsMemberReceiveAddresses;
    }


    @ResponseBody
    @RequestMapping("index")
    public String index(){
        return "hello world";
    }

}
