package com.zslin.basic.model;

import javax.persistence.*;

/**
 * Created by zsl-pc on 2016/9/1.
 */
@Entity
@Table(name="a_role_menu")
public class RoleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 角色Id */
    private Integer rid;

    /** 菜单Id */
    private Integer mid;

    public Integer getId() {
        return id;
    }

    public Integer getMid() {
        return mid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
