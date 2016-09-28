package com.broto.service;

import com.broto.backstage.entity.Action;
import com.broto.backstage.entity.Module;
import com.broto.backstage.service.ActionService;
import com.broto.backstage.service.ModuleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * Created by yitao on 2016/9/1.
 */
@ContextConfiguration(locations = {"/applicationContext-*test.xml"})
public class ActionServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    ModuleService moduleService;
    @Autowired
    ActionService actionService;

    @Test
    public void testSave(){
        Module module = moduleService.findOneByCode("TEST");
        String moduleId= module==null?"":module.getId();
        Action action = new Action(0,"","action3","","",moduleId,"/test3",false,true,"");
        actionService.save(action);
        System.out.println();
        System.out.println(action.getId());
        System.out.println();
    }

    @Test
    public void testDelete(){
        String actionId = "051eacbd23bd4658baf6ef6a2377ca3b";
        actionService.delete(actionId,true);
    }

    @Test
    public void testFindAll(){
        List<Action> action = actionService.findAll(null,null);
        System.out.println();
        System.out.println(action.size());
        System.out.println();
    }

}
