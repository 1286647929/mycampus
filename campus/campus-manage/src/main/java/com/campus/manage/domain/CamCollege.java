package com.campus.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.campus.common.annotation.Excel;
import com.campus.common.core.domain.BaseEntity;

/**
 * 学院管理对象 cam_college
 * 
 * @author ay
 * @date 2022-08-17
 */
public class CamCollege extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学院id */
    private Long collegeId;

    /** 学院名称 */
    @Excel(name = "学院名称")
    private String collegeName;

    /** 学院金额 */
    @Excel(name = "学院金额")
    private Long collegeMoney;

    public void setCollegeId(Long collegeId) 
    {
        this.collegeId = collegeId;
    }

    public Long getCollegeId() 
    {
        return collegeId;
    }
    public void setCollegeName(String collegeName) 
    {
        this.collegeName = collegeName;
    }

    public String getCollegeName() 
    {
        return collegeName;
    }
    public void setCollegeMoney(Long collegeMoney) 
    {
        this.collegeMoney = collegeMoney;
    }

    public Long getCollegeMoney() 
    {
        return collegeMoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("collegeId", getCollegeId())
            .append("collegeName", getCollegeName())
            .append("collegeMoney", getCollegeMoney())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
