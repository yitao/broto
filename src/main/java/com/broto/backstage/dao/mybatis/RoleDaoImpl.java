package com.broto.backstage.dao.mybatis;

import com.broto.backstage.dao.RoleDao;
import com.broto.backstage.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository("P_RoleDaoImpl")
public class RoleDaoImpl extends BaseSqlDaoImpl<Role,String> implements RoleDao{
}
