package com.broto.backstage.dao.mybatis;

import com.broto.backstage.dao.ActionDao;
import com.broto.backstage.entity.Action;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2016/5/19.
 */
@Repository
public class ActionDaoImpl extends BaseSqlDaoImpl<Action, String> implements ActionDao {

    @Override
    public List<Action> findAllByModuleId(String moduleId) {
        Map<String,Object> query = new HashMap<>();
        query.put("module_id",moduleId);
        List<Action> result = findAllByMap(query);
        return result;
    }

}
