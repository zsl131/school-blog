package com.zslin.web.service;

import com.zslin.web.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 0:34.
 */
public interface ICategoryService extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    @Query("UPDATE Category c SET c.name=?3 WHERE c.id=?1 AND c.accountId=?2")
    @Modifying
    @Transactional
    void updateName(Integer id, Integer account, String name);

    @Query("DELETE FROM Category c WHERE c.id=?1 AND c.accountId=?2")
    @Modifying
    @Transactional
    void delete(Integer id, Integer accountId);
}
