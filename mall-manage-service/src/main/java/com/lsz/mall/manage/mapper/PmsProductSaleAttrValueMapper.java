package com.lsz.mall.manage.mapper;

import com.lsz.mall.bean.PmsProductInfo;
import com.lsz.mall.bean.PmsProductSaleAttr;
import com.lsz.mall.bean.PmsProductSaleAttrValue;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface PmsProductSaleAttrValueMapper extends Mapper<PmsProductSaleAttrValue> {
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(Map<String, String> map);

    List<PmsProductInfo> selectSkuSaleAttrValueListBySpu(@Param("spuId") String spuId);
}
