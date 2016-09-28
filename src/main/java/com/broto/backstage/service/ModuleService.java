package com.broto.backstage.service;

import com.broto.backstage.dao.ModuleDao;
import com.broto.backstage.entity.Module;
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


}
