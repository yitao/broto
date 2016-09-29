package com.broto.service;

import com.broto.backstage.entity.*;
import com.broto.backstage.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * Created by yitao on 2016/9/28.
 */
@ContextConfiguration(locations = {"/applicationContext-*test.xml"})
public class PermissionServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    ModuleService moduleService;
    @Autowired
    ActionService actionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;

    @Test
    public void testSaveRMA() {
        System.out.println();
        Role role = roleService.findOneByCode("TEST");
        Module module = moduleService.findOneByCode("TEST");
        if (role != null && module != null) {
            List<Action> actions = actionService.findAllByModuleId(module.getId());
            for (Action aciton : actions) {
                RMA rma = permissionService.saveRMA(role.getId(), module.getId(), aciton.getId());
                System.out.println(rma);
            }
        }
        System.out.println();
    }

    @Test
    public void testDeleteRMA(){
        System.out.println();
        Role role = roleService.findOneByCode("TEST");
        Module module = moduleService.findOneByCode("TEST");
        if (role != null && module != null) {
            List<Action> actions = actionService.findAllByModuleId(module.getId());
            for (Action aciton : actions) {
                permissionService.deleteRMA(role.getId(), module.getId(), aciton.getId());
            }
        }
        System.out.println();
    }

    @Test
    public void testSaveAR(){
        System.out.println();
        Account account = accountService.findOneByCode("TEST");
        Role role = roleService.findOneByCode("TEST");
        if (role != null && account != null) {
            AR ar = permissionService.saveAR(account.getId(),role.getId());
            System.out.println(ar);
        }
        System.out.println();
    }

    @Test
    public void testDeleteAR(){
        System.out.println();
        Account account = accountService.findOneByCode("TEST");
        Role role = roleService.findOneByCode("TEST");
        if (role != null && account != null) {
            permissionService.deleteAR(account.getId(),role.getId());
        }
        System.out.println();
    }

    @Test
    public void testSaveR2R(){
        Role role = roleService.findOneByCode("TEST");
        if(role!=null){
            R2R r2r = new R2R(role.getId(),role.getId());
            permissionService.saveR2R(r2r);
            System.out.println(r2r);
        }
    }

    @Test
    public void testDeleteR2R(){
        Role role = roleService.findOneByCode("TEST");
        if(role!=null){
            R2R r2r = new R2R(role.getId(),role.getId());
            permissionService.deleteR2R(r2r);
            System.out.println(r2r);
        }
    }

    @Test
    public void testSaveR2MA() {
        System.out.println();
        Role role = roleService.findOneByCode("TEST");
        Module module = moduleService.findOneByCode("TEST");
        if (role != null && module != null) {
            List<Action> actions = actionService.findAllByModuleId(module.getId());
            for (Action aciton : actions) {
                R2MA rma = permissionService.saveR2MA(role.getId(), module.getId(), aciton.getId());
                System.out.println(rma);
            }
        }
        System.out.println();
    }

    @Test
    public void testDeleteR2MA(){
        System.out.println();
        Role role = roleService.findOneByCode("TEST");
        Module module = moduleService.findOneByCode("TEST");
        if (role != null && module != null) {
            List<Action> actions = actionService.findAllByModuleId(module.getId());
            for (Action aciton : actions) {
                permissionService.deleteR2MA(role.getId(), module.getId(), aciton.getId());
            }
        }
        System.out.println();
    }


}
