package com.campus.manage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.campus.common.annotation.Excel;
import com.campus.common.core.domain.BaseEntity;

/**
 * 离校管理对象 cam_levscl
 * 
 * @author ay
 * @date 2022-08-17
 */
public class CamLevscl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private Long studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String studentName;

    /** 学院名称 */
    @Excel(name = "学院名称")
    private String collegeName;

    /** 班级 */
    @Excel(name = "班级")
    private String studentClass;

    /** 离校原因 */
    @Excel(name = "离校原因")
    private String levCourse;

    /** 行程轨迹 */
    @Excel(name = "行程轨迹")
    private String travel;

    /** 离校时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离校时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date levTime;

    /** 返校时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "返校时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reschoolTime;

    /** 审批状态 */
    @Excel(name = "审批状态")
    private String status;

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setCollegeName(String collegeName) 
    {
        this.collegeName = collegeName;
    }

    public String getCollegeName() 
    {
        return collegeName;
    }
    public void setStudentClass(String studentClass) 
    {
        this.studentClass = studentClass;
    }

    public String getStudentClass() 
    {
        return studentClass;
    }
    public void setLevCourse(String levCourse) 
    {
        this.levCourse = levCourse;
    }

    public String getLevCourse() 
    {
        return levCourse;
    }
    public void setTravel(String travel) 
    {
        this.travel = travel;
    }

    public String getTravel() 
    {
        return travel;
    }
    public void setLevTime(Date levTime) 
    {
        this.levTime = levTime;
    }

    public Date getLevTime() 
    {
        return levTime;
    }
    public void setReschoolTime(Date reschoolTime) 
    {
        this.reschoolTime = reschoolTime;
    }

    public Date getReschoolTime() 
    {
        return reschoolTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("collegeName", getCollegeName())
            .append("studentClass", getStudentClass())
            .append("levCourse", getLevCourse())
            .append("travel", getTravel())
            .append("levTime", getLevTime())
            .append("reschoolTime", getReschoolTime())
            .append("status", getStatus())
            .toString();
    }
}
