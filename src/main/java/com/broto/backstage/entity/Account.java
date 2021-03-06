package com.broto.backstage.entity;

import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by yitao on 2016/5/11.
 */

public class Account extends BaseDeletedDataEntity {
    String account;
    String password;
    boolean inuse;
    String code;

    @Transient
    List<Role> roles;

    public Account() {
    }

    public Account(String account, String password, boolean inuse, String code) {
        this.account = account;
        this.password = password;
        this.inuse = inuse;
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInuse() {
        return inuse;
    }

    public void setInuse(boolean inuse) {
        this.inuse = inuse;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
