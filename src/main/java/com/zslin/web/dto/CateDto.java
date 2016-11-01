package com.zslin.web.dto;

/**
 * Created by 钟述林 393156105@qq.com on 2016/11/1 10:18.
 */
public class CateDto {

    private Integer cateId;

    private String cateName;

    private Long amount;

    public CateDto() {}

    public CateDto(Integer cateId, String cateName, Long amount) {
        this.cateId = cateId; this.cateName = cateName;
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public Integer getCateId() {
        return cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
