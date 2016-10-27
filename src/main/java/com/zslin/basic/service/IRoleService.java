package com.zslin.basic.service;

import com.zslin.basic.model.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/19 9:59.
 */
public interface IRoleService extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    @Query("SELECT rm.mid FROM RoleMenu rm WHERE rm.rid=?1")
    List<Integer> listRoleMenuIds(Integer roleId);
}
