package com.zslin.web.service;

import com.zslin.web.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 钟述林 393156105@qq.com on 2016/10/28 0:33.
 */
public interface IAccountService extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

    /** 通过邮箱获取对象 */
    Account findByEmail(String email);
}
