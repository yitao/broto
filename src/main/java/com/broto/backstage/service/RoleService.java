package com.broto.backstage.service;

import com.broto.backstage.dao.RoleDao;
import com.broto.backstage.entity.Action;
import com.broto.backstage.entity.Role;
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
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    
    //保存（插入或者更新）
    public void save(Role role){
        roleDao.save(role);
    }

    //删除
    public void delete(String roleId,boolean isHardDelete){
        if(StringUtils.isNotBlank(roleId)){
            roleDao.delete(roleId,isHardDelete);
        }
    }

    //查询
    public List<Role> findAll(){
        return findAll(null);
    }

    public List<Role> findAll(Boolean deleted){
        Map<String,Object> query = new HashMap<>();
        if(deleted!=null) {
            query.put("deleted", deleted);
        }
        List<Role> result = roleDao.findAllByMap(query);
        return result;
    }


    public Role findOneByCode(String code){
        Map<String,Object> query = new HashMap<>();
        query.put("code", code);
        List<Role> result = roleDao.findAllByMap(query);
        if(CollectionUtils.isNotEmpty(result)){
            return result.get(0);
        }
        return null;
    }


}
