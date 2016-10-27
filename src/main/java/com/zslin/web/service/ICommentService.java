package com.zslin.web.service;

import com.zslin.web.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 0:37.
 */
public interface ICommentService extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
}
