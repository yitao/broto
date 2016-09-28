package com.broto.backstage.service;

import com.broto.backstage.dao.ModuleDao;
import com.broto.backstage.entity.Action;
import com.broto.backstage.entity.Module;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模块service
 * Created by yitao on 2016/9/28.
 */
@Service
public class ModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private ActionService actionService;

    //保存（插入或者更新）
    public void save(Module module){
       moduleDao.save(module);
    }

    //删除
    public void delete(String moduleId,boolean isHardDelete){
        if(StringUtils.isNotBlank(moduleId)){
            moduleDao.delete(moduleId,isHardDelete);
        }
    }


    public Module findOneByCode(String code){
        Map<String,Object> query = new HashMap<>();
        query.put("code", code);
        List<Module> result = moduleDao.findAllByMap(query);
        if(CollectionUtils.isNotEmpty(result)){
            return result.get(0);
        }
        return null;
    }

    //查询
    public List<Module> findAll(Boolean show,Boolean deleted){
        Map<String,Object> query = new HashMap<>();
        if(show!=null) {
            query.put("show", show);
        }
        if(deleted!=null) {
            query.put("deleted", deleted);
        }
        List<Module> result = moduleDao.findAllByMap(query);
        return result;
    }

    public List<Module> findAllModuleAndAction(Boolean show,Boolean deleted){
        List<Module> modules = findAll(show,deleted);
        for(Module module : modules ){
            List<Action> actions = actionService.findAll(module.getId(),show,deleted);
            module.setActions(actions);
        }
        return modules;
    }


}
