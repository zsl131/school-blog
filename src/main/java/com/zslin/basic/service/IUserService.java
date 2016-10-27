package com.zslin.basic.service;

import com.zslin.basic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/19 10:00.
 */
public interface IUserService extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    @Query("SELECT ur.rid FROM UserRole ur WHERE ur.uid=?1")
    List<Integer> listUserRoleIds(Integer userId);
}
