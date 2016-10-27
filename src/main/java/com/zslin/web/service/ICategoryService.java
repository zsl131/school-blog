package com.zslin.web.service;

import com.zslin.web.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 0:34.
 */
public interface ICategoryService extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
}
