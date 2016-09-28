package com.broto.backstage.entity;

import org.springframework.data.annotation.Transient;

/**
 * Created by yitao on 2016/5/11.
 */
public class Action extends BaseLabelEntity implements Cloneable {
    String moduleId;
    String action;

    boolean exclude;//是否除外，黑名单为true,白名单为false，默认为白名单
    @Transient
    int state;//勾选状态
    boolean show;

    public Action() {
    }

    public Action(long order, String icon, String label, String hint, String desc, String moduleId, String action, boolean exclude,boolean show) {
        super(order, icon, label,  hint, desc);
        this.moduleId = moduleId;
        this.action = action;
        this.exclude = exclude;
        this.show = show;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isExclude() {
        return exclude;
    }

    public void setExclude(boolean exclude) {
        this.exclude = exclude;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public Action clone() {
        Action a = new Action(order, icon, label, hint, desc, moduleId, action, exclude,show);
        return a;
    }
}
