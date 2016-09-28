package com.broto.service;

import com.broto.backstage.entity.Module;
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
public class ModuleServiceTest  extends AbstractJUnit4SpringContextTests {
    @Autowired
    ModuleService moduleService;

    @Test
    public void testSave(){
        Module module = new Module(0,"","模块1","","","",true);
        moduleService.save(module);
        System.out.println();
        System.out.println(module.getId());
        System.out.println();
    }

    @Test
    public void testDelete(){
        String moduleId = "e2d51d54d3904ea18e0d05d26c00d9ee";
        moduleService.delete(moduleId,false);
    }

    @Test
    public void testFindAll(){
        List<Module> modules = moduleService.findAll(true,null);
        System.out.println();
        System.out.println(modules.size());
        System.out.println();
    }

}
