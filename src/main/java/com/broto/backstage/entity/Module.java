package com.broto.backstage.entity;

import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yitao on 2016/5/11.
 */
public class Module extends BaseLabelEntity implements Cloneable{
    String faModuleId;
    boolean show;
    String code;

    @Transient
    int state;

    public Module() {
    }

    public Module(long order, String icon, String label, String hint, String desc, String faModuleId, boolean show,String code) {
        super(order, icon, label, hint, desc);
        this.faModuleId = faModuleId;
        this.show = show;
        this.code = code;
    }

    @Transient
    List<Module> subModules;
    @Transient
    List<Action> actions;

    public String getFaModuleId() {
        return faModuleId;
    }

    public void setFaModuleId(String faModuleId) {
        this.faModuleId = faModuleId;
    }

    public List<Module> getSubModules() {
        return subModules;
    }

    public void setSubModules(List<Module> subModules) {
        this.subModules = subModules;
    }

    public List<Action> getActions() {
        return actions==null?new ArrayList<Action>():actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }



    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Module clone() {
        Module module = new Module( order, icon, label,  hint, desc, faModuleId, show,code);
        if(this.actions!=null){
            List<Action> nas = new ArrayList<>();
            for(Action a : this.actions){
                Action na = a.clone();
                nas.add(na);
            }
            module.setActions(nas);
        }
        return module;
    }
}
