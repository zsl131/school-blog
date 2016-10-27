package com.zslin.web.model;

import javax.persistence.*;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 0:26.
 */
@Entity
@Table(name = "t_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "account_email")
    private String accountEmail;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "order_no")
    private Integer orderNo;

    /** 是否为导航 */
    @Column(name = "is_nav")
    private Integer isNav;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getIsNav() {
        return isNav;
    }

    public void setIsNav(Integer isNav) {
        this.isNav = isNav;
    }
}
