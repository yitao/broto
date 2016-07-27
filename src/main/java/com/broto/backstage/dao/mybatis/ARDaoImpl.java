package com.broto.backstage.dao.mybatis;

import com.broto.backstage.dao.ARDao;
import com.broto.backstage.entity.AR;
import org.springframework.stereotype.Repository;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_ARDaoImpl")
public class ARDaoImpl extends BaseSqlDaoImpl<AR, String> implements ARDao {

    @Override
    public void deleteByAccountId(String accountId) {
        getSqlSession().delete(className+".deleteByAccountId",accountId);
    }

    @Override
    public void deleteByRoleId(String roleId) {
        getSqlSession().delete(className+".deleteByRoleId",roleId);
    }

    public void delete(AR ar) {
        getSqlSession().delete(className+".delete",ar);
    }
}
