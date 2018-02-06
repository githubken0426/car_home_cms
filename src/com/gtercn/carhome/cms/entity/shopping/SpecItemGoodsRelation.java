package com.gtercn.carhome.cms.entity.shopping;

public class SpecItemGoodsRelation {
    private Integer id;
    private String goodsId;
    private String specItemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id ;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getSpecItemId() {
        return specItemId;
    }

    public void setSpecItemId(String specItemId) {
        this.specItemId = specItemId == null ? null : specItemId.trim();
    }
}