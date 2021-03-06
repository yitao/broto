package com.broto.backstage.dao;

import com.broto.backstage.entity.Action;

import java.util.List;

/**
 * Created by yitao on 2016/5/19.
 */
public interface ActionDao extends BaseSqlDao<Action, String>{

    List<Action> findAllByModuleId(String moduleId);

}
