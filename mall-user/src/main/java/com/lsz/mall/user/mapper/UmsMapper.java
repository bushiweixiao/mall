package com.lsz.mall.user.mapper;

import com.lsz.mall.bean.UmsMember;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UmsMapper extends tk.mybatis.mapper.common.Mapper<UmsMember> {
    public List<UmsMember> selectAllUser();

}
