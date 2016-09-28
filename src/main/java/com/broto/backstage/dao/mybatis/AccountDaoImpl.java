package com.broto.backstage.dao.mybatis;

import com.broto.backstage.dao.AccountDao;
import com.broto.backstage.entity.Account;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository
public class AccountDaoImpl extends BaseSqlDaoImpl<Account,String> implements AccountDao {
    @Override
    public Account findOneByAccount(String account) {
        Map<String,Object> query = new HashMap<>();
        query.put("account",account);
        List<Account> accountList = findAllByMap(query);
        if(CollectionUtils.isEmpty(accountList)){
            return null;
        }else{
            return accountList.get(0);
        }
    }
}
