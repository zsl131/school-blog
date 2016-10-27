package com.zslin.basic.model;

import javax.persistence.*;

/**
 * Created by zsl-pc on 2016/9/1.
 */
@Entity
@Table(name="a_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /** 用户Id */
    private Integer uid;

    /** 角色Id */
    private Integer rid;

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUid() {
        return uid;
    }
}
