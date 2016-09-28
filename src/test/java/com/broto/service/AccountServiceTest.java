package com.broto.service;

import com.broto.backstage.entity.Account;
import com.broto.backstage.service.ActionService;
import com.broto.backstage.service.ModuleService;
import com.broto.backstage.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

/**
 * Created by yitao on 2016/9/1.
 */
@ContextConfiguration(locations = {"/applicationContext-*test.xml"})
public class AccountServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    ModuleService moduleService;
    @Autowired
    ActionService actionService;
    @Autowired
    private AccountService accountService;

    @Test
    public void testSave(){
        Account account = new Account("test","test",false,"TEST");
        accountService.save(account);
        System.out.println();
        System.out.println(account.getId());
        System.out.println();
    }

    @Test
    public void testDelete(){
        String accountId = "204720f980e64ed098f4b1c2316e527c";
        accountService.delete(accountId,true);
    }

    @Test
    public void testFindAll(){
        List<Account> accounts = accountService.findAll(null);
        System.out.println();
        System.out.println(accounts.size());
        System.out.println();
    }

    @Test
    public void testFindOneByCode(){
        String code = "TEST";
        Account account = accountService.findOneByCode(code);
        System.out.println();
        System.out.println(account);
        System.out.println();
    }

}
