package com.zslin.basic.model;

import javax.persistence.*;

/**
 * Created by zsl-pc on 2016/9/1.
 */
@Entity
@Table(name="a_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * 菜单的名称，中文显示名称
     */
    private String name;
    /**
     * 菜单的唯一英文标识，如:user,auth等
     */
    private String sn;

    /**
     * 菜单的顺序
     */
    @Column(name="order_num")
    private int orderNum;
    /**
     * 是否显示，0表示不显示，1表示显示
     */
    private int display;

    /**
     * 菜单的链接地址
     */
    private String href;
    /**
     * 上一级菜单
     */
    private Integer pid;

    private String pname;
    /**
     * 父类的sn
     */
    private String psn;

    /**
     * 菜单的图标
     */
    private String icon;

    /** 菜单类型，1：导航菜单；2：权限菜单 */
    private String type;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
