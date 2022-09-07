package com.campus.manage.domain;

import com.campus.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 各大省份疫情数据
 *
 * @author yyy
 * @date 2022/9/4 23:33
 */
public class CamNovData extends BaseEntity {
    private static final Long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("name",getName())
                .append("value",getValue())
                .append("updateTime",getUpdateTime())
                .toString();
    }
}
