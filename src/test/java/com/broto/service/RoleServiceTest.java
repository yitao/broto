package com.broto.service;

import com.broto.backstage.entity.Action;
import com.broto.backstage.entity.Module;
import com.broto.backstage.entity.Role;
import com.broto.backstage.service.ActionService;
import com.broto.backstage.service.ModuleService;
import com.broto.backstage.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * Created by yitao on 2016/9/1.
 */
@ContextConfiguration(locations = {"/applicationContext-*test.xml"})
public class RoleServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    ModuleService moduleService;
    @Autowired
    ActionService actionService;
    @Autowired
    private RoleService roleService;

    @Test
    public void testSave(){
        Role role = new Role("测试员2","测试员2","TEST2",false,false);
        roleService.save(role);
        System.out.println();
        System.out.println(role.getId());
        System.out.println();
    }

    @Test
    public void testDelete(){
        String roleId = "a464a50e7b834032b2b7770294238c64";
        roleService.delete(roleId,true);
    }

    @Test
    public void testFindAll(){
        List<Role> roles = roleService.findAll(null);
        System.out.println();
        System.out.println(roles.size());
        System.out.println();
    }

    @Test
    public void testFindOneByCode(){
        String code = "TEST";
        Role role = roleService.findOneByCode(code);
        System.out.println();
        System.out.println(role);
        System.out.println();
    }

}
