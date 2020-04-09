package com.lsz.mall.manage.service.impl;

import com.lsz.mall.bean.PmsSkuAttrValue;
import com.lsz.mall.bean.PmsSkuImage;
import com.lsz.mall.bean.PmsSkuInfo;
import com.lsz.mall.bean.PmsSkuSaleAttrValue;
import com.lsz.mall.manage.mapper.PmsSkuAttrValueMapper;
import com.lsz.mall.manage.mapper.PmsSkuImageMapper;
import com.lsz.mall.manage.mapper.PmsSkuInfoMapper;
import com.lsz.mall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.lsz.mall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
//    @Autowired
//    RedisUtil redisUtil;

    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        PmsSkuInfo skuInfo = new PmsSkuInfo();

        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setId(skuId);

        PmsSkuInfo pmsSkuInfo = pmsSkuInfoMapper.selectOne(skuInfo);
        pmsSkuInfo.setPmsSkuImageList(pmsSkuImageMapper.select(pmsSkuImage));
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuListBySpuId(String spuId) {
        PmsSkuInfo skuInfo = new PmsSkuInfo();
        skuInfo.setId(spuId);
        return pmsSkuInfoMapper.select(skuInfo);
    }

    @Override
    public void saveSku(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfoMapper.insert(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();

        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getPmsSkuAttrValueList();
        for (PmsSkuAttrValue pmsskuAttrValue :
                skuAttrValueList) {
            pmsskuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insert(pmsskuAttrValue);
        }

        List<PmsSkuSaleAttrValue> saleAttrValueList = pmsSkuInfo.getPmsSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue skuSaleAttrValue :
                saleAttrValueList) {
            skuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insert(skuSaleAttrValue);
        }

        List<PmsSkuImage> skuImageList = pmsSkuInfo.getPmsSkuImageList();
        for (PmsSkuImage pmsSkuImage :
                skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insert(pmsSkuImage);
        }

    }

    //    @Override
//    public SkuInfo getSkuById(String skuId) {
//        //redis缓存和缓存锁应该为不同的节点，这里简化为在同一个节点上
//
//        Jedis jedis = redisUtil.getJedis();
//        SkuInfo skuInfo = null;
//
//        //查询redis缓存
//        String key = "sku:" + skuId + ":info";
//        String val = jedis.get(key);
//        //此数据在数据库中也没有，直接返回空值
//        if ("empty".equals(val)) {
//            return skuInfo;
//        }
//
//        if (StringUtils.isBlank(val)) {//没有命中缓存，查询数据库
//
//            //申请缓存锁
//            String OK = jedis.set("sku:" + skuId + ":lock", "1", "nx", "px", 3000);
//
//            if ("OK".equals(OK)) {//拿到缓存锁
//                //查询数db
//                skuInfo = getSkuByIdFormDb(skuId);
//
//                if (skuInfo != null) {
//                    //同步缓存
//                    jedis.set(key, JSON.toJSONString(skuInfo));
//                } else {
//                    //通知同伴
//                    jedis.setex("sku:" + skuId + ":info", 10, "empty");
//                }
//                //归还锁
//                jedis.del("sku:" + skuId + ":lock");
//
//            } else {//没有拿到缓存锁
//                //自旋
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                getSkuById(skuId);
//
//            }
//        } else {//查询的数据在缓存中
//            skuInfo = JSON.parseObject(val, SkuInfo.class);
//        }
//        return skuInfo;
//    }
//
    @Override
    public List<PmsSkuInfo> getSkuListByCatalog3Id(String catalog3Id) {
        PmsSkuInfo skuInfo = new PmsSkuInfo();
        skuInfo.setCatalog3Id(catalog3Id);
        List<PmsSkuInfo> skuInfos = pmsSkuInfoMapper.select(skuInfo);

        for (PmsSkuInfo sku :
                skuInfos) {
            PmsSkuAttrValue skuAttrValue = new PmsSkuAttrValue();
            skuAttrValue.setSkuId(sku.getId());
            List<PmsSkuAttrValue> skuAttrValues = pmsSkuAttrValueMapper.select(skuAttrValue);
            sku.setPmsSkuAttrValueList(skuAttrValues);
        }

        return skuInfos;
    }

    @Override
    public boolean checkPrice(BigDecimal skuPrice, String skuId) {
        boolean bPrice = false;
        PmsSkuInfo skuInfo = new PmsSkuInfo();
        skuInfo.setId(skuId);
        PmsSkuInfo sku = pmsSkuInfoMapper.selectOne(skuInfo);
        if (sku != null) {
            int i = sku.getPrice().compareTo(skuPrice);
            if (i == 0) {
                bPrice = true;
            }
        }

        return bPrice;
    }

    private PmsSkuInfo getSkuByIdFormDb(String skuId) {
        PmsSkuInfo skuInfo = new PmsSkuInfo();
        skuInfo.setId(skuId);
        PmsSkuInfo sku = pmsSkuInfoMapper.selectOne(skuInfo);

        PmsSkuImage skuImage = new PmsSkuImage();
        skuImage.setSkuId(skuId);
        List<PmsSkuImage> skuImages = pmsSkuImageMapper.select(skuImage);
        sku.setPmsSkuImageList(skuImages);

        PmsSkuSaleAttrValue skuSaleAttrValue = new PmsSkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuId);
        List<PmsSkuSaleAttrValue> skuSaleAttrValues = pmsSkuSaleAttrValueMapper.select(skuSaleAttrValue);
        sku.setPmsSkuSaleAttrValueList(skuSaleAttrValues);
        return sku;
    }


}
