package com.broto.backstage.service;

import com.broto.backstage.dao.ActionDao;
import com.broto.backstage.entity.Action;
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
public class ActionService {

    @Autowired
    private ActionDao actionDao;


    //保存（插入或者更新）
    public void save(Action action) {
        actionDao.save(action);
    }

    //删除
    public void delete(String moduleId, boolean isHardDelete) {
        if (StringUtils.isNotBlank(moduleId)) {
            actionDao.delete(moduleId, isHardDelete);
        }
    }

    //查询

    public List<Action> findAllByCode(String code) {
        Map<String, Object> query = new HashMap<>();
        query.put("code", code);
        List<Action> result = actionDao.findAllByMap(query);
        return result;
    }


    public Action findOneByCode(String code) {
        List<Action> result = findAllByCode(code);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    public List<Action> findAll(Boolean show, Boolean deleted) {
        return findAll(null, show, deleted);
    }

    public List<Action> findAll(String moduleId, Boolean show, Boolean deleted) {
        Map<String, Object> query = new HashMap<>();
        if (StringUtils.isNotBlank(moduleId)) {
            query.put("module_id", moduleId);
        }
        if (show != null) {
            query.put("show", show);
        }
        if (deleted != null) {
            query.put("deleted", deleted);
        }
        List<Action> result = actionDao.findAllByMap(query);
        return result;
    }

    public List<Action> findAllByModuleId(String moduleId) {
        return findAll(moduleId, null, null);
    }


}
