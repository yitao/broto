package com.broto.backstage.dao.mybatis;

import com.broto.backstage.dao.ModuleDao;
import com.broto.backstage.entity.Module;
import org.springframework.stereotype.Repository;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository
public class ModuleDaoImpl extends BaseSqlDaoImpl<Module, String> implements ModuleDao {

}
