package com.broto.backstage.dao;

import com.broto.backstage.entity.Account;

/**
 * Created by yitao on 2016/5/19.
 */
public interface AccountDao extends BaseSqlDao<Account, String> {
    /**
     * 根据账号名称查询账号
     * @param account
     * @return
     */
    Account findOneByAccount(String account);
}
