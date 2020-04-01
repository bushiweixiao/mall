package com.lsz.mall.service;

import com.lsz.mall.bean.UmsMember;
import com.lsz.mall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberIdExmaple(String memberId);
}
