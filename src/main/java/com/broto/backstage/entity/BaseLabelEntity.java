package com.broto.backstage.entity;

/**
 * Created by yitao on 2016/5/11.
 */
public class BaseLabelEntity extends BaseDeletedDataEntity{
    String icon;
    String label;
    String hint;
    String desc;

    public BaseLabelEntity() {
    }

    public BaseLabelEntity(long order,String icon, String label, String hint, String desc) {
        this.order = order;
        this.icon = icon;
        this.label = label;
        this.hint = hint;
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
