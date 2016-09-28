package com.broto.backstage.entity;

import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * Created by yitao on 2016/5/11.
 */
public class Role extends BaseDeletedDataEntity {
    String name;
    String desc;
    boolean inuse;
    boolean ap;
    String code;

    @Transient
    List<Module> modules;
    @Transient
    List<Role> openedRoles;
    @Transient
    List<Module> openedModules;
    @Transient
    int state;

    public Role() {
    }

    public Role(String name, String desc,String code, boolean inuse,boolean ap) {
        this.name = name;
        this.desc = desc;
        this.code = code;
        this.inuse = inuse;
        this.ap = ap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean getInuse() {
        return inuse;
    }

    public void setInuse(boolean inuse) {
        this.inuse = inuse;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Role> getOpenedRoles() {
        return openedRoles;
    }

    public void setOpenedRoles(List<Role> openedRoles) {
        this.openedRoles = openedRoles;
    }

    public List<Module> getOpenedModules() {
        return openedModules;
    }

    public void setOpenedModules(List<Module> openedModules) {
        this.openedModules = openedModules;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
