package com.broto.backstage.service;

import com.broto.backstage.dao.AccountDao;
import com.broto.backstage.entity.Account;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2016/9/28.
 */
@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    //保存（插入或者更新）
    public void save(Account account) {
        accountDao.save(account);
    }

    //删除
    public void delete(String accountId, boolean isHardDelete) {
        if (StringUtils.isNotBlank(accountId)) {
            accountDao.delete(accountId, isHardDelete);
        }
    }

    //查询
    public List<Account> findAll(Boolean deleted) {
        Map<String, Object> query = new HashMap<>();
        if (deleted != null) {
            query.put("deleted", deleted);
        }
        List<Account> result = accountDao.findAllByMap(query);
        return result;
    }

    public List<Account> findAllByCode(String code) {
        Map<String, Object> query = new HashMap<>();
        query.put("code", code);
        List<Account> result = accountDao.findAllByMap(query);
        return result;
    }

    public Account findOneByCode(String code) {
        List<Account> result = findAllByCode(code);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    public Account findOneByAccount(String account) {
        Map<String, Object> query = new HashMap<>();
        query.put("account", account);
        List<Account> accountList = accountDao.findAllByMap(query);
        if (CollectionUtils.isEmpty(accountList)) {
            return null;
        } else {
            return accountList.get(0);
        }
    }
}
