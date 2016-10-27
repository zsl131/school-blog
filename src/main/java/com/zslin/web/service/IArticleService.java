package com.zslin.web.service;

import com.zslin.web.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 0:35.
 */
public interface IArticleService extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    @Query("UPDATE Article art SET art.readCount=art.readCount+?2 WHERE art.id=?1")
    @Modifying
    @Transactional
    void updateReadCount(Integer id, Integer amount);

    @Query("UPDATE Article art SET art.commentCount=art.commentCount+?2 WHERE art.id=?1")
    @Modifying
    @Transactional
    void updateCommentCount(Integer id, Integer amount);
}
