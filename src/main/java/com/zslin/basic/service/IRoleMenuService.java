package com.zslin.basic.service;

import com.zslin.basic.model.Role;
import com.zslin.basic.model.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by zsl-pc on 2016/9/1.
 */
public interface IRoleMenuService extends JpaRepository<RoleMenu, Integer>, JpaSpecificationExecutor<Role> {

    @Query("SELECT rm.mid FROM RoleMenu rm WHERE rm.rid=:roleId")
    List<Integer> queryMenuIds(@Param("roleId") Integer roleId);

    RoleMenu queryByRidAndMid(Integer rid, Integer mid);
}
