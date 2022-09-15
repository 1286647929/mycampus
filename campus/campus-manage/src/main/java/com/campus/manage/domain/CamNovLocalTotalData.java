package com.campus.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/12 16:20
 */
public class CamNovLocalTotalData {
    private static final Long serialVersionUID = 1L;

    private String name;
    private Long confirm;
    private Long suspect;
    private Long heal;
    private Long dead;
    private Long severe;
    private Long input;
    private String updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getConfirm() {
        return confirm;
    }

    public void setConfirm(Long confirm) {
        this.confirm = confirm;
    }

    public Long getSuspect() {
        return suspect;
    }

    public void setSuspect(Long suspect) {
        this.suspect = suspect;
    }

    public Long getHeal() {
        return heal;
    }

    public void setHeal(Long heal) {
        this.heal = heal;
    }

    public Long getDead() {
        return dead;
    }

    public void setDead(Long dead) {
        this.dead = dead;
    }

    public Long getSevere() {
        return severe;
    }

    public void setSevere(Long severe) {
        this.severe = severe;
    }

    public Long getInput() {
        return input;
    }

    public void setInput(Long input) {
        this.input = input;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("name",getName())
                .append("confirm",getConfirm())
                .append("suspect",getSuspect())
                .append("heal",getHeal())
                .append("dead",getDead())
                .append("severe",getSevere())
                .append("input",getInput())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
