package com.campus.manage.domain;

import com.campus.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 疫情地图数据——当天
 *
 * @author yyy
 * @date 2022/9/4 22:18
 */
public class CamNovChinaTotalToday extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long Id;

    private Long confirm;

    private Long suspect;

    private Long heal;

    private Long dead;

    private Long severe;

    private Long input;

    private Long incrNoSymptom;

    private Long storeconfirm;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Long getStoreconfirm() {
        return storeconfirm;
    }

    public void setStoreconfirm(Long storeconfirm) {
        this.storeconfirm = storeconfirm;
    }

    public Long getIncrNoSymptom() {
        return incrNoSymptom;
    }

    public void setIncrNoSymptom(Long incrNoSymptom) {
        this.incrNoSymptom = incrNoSymptom;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("confirm",getConfirm())
                .append("suspect",getSuspect())
                .append("heal",getHeal())
                .append("dead",getDead())
                .append("severe",getSevere())
                .append("input",getInput())
                .append("incrNoSymptom",getIncrNoSymptom())
                .append("storeconfirm",getStoreconfirm())
                .toString();
    }
}
