package com.broto;

import com.broto.backstage.dao.ModuleDao;
import com.broto.backstage.entity.Module;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by yitao on 2016/7/27.
 */
@ContextConfiguration(locations = {"/applicationContext-*test.xml"})
public class HolderTest extends AbstractJUnit4SpringContextTests{

    @Autowired
    ModuleDao moduleDao;
    @Test
    public void test(){
        System.out.println();
        System.out.println("----o---");
        Module module = new Module();
        module.setLabel("模块");
        moduleDao.save(module);
        System.out.println("----ok---");
    }
}
