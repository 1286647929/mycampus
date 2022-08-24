package com.campus.manage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.campus.common.annotation.Excel;
import com.campus.common.core.domain.BaseEntity;

/**
 * 健康打卡对象 cam_health
 * 
 * @author ay
 * @date 2022-08-17
 */
public class CamHealth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private Long studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String studentName;

    /** 学院 */
    @Excel(name = "学院")
    private String collegeName;

    /** 班级 */
    @Excel(name = "班级")
    private String studentClass;

    /** 打卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "打卡时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date punchTime;

    /** 时间段 */
    @Excel(name = "时间段")
    private String tiimePart;

    /** 行程轨迹 */
    @Excel(name = "行程轨迹")
    private String travel;

    /** 体温 */
    @Excel(name = "体温")
    private String temperature;

    /** 咳嗽 */
    @Excel(name = "咳嗽")
    private String cough;

    /** 发烧 */
    @Excel(name = "发烧")
    private String fever;

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
    public void setPunchTime(Date punchTime) 
    {
        this.punchTime = punchTime;
    }

    public Date getPunchTime() 
    {
        return punchTime;
    }
    public void setTiimePart(String tiimePart) 
    {
        this.tiimePart = tiimePart;
    }

    public String getTiimePart() 
    {
        return tiimePart;
    }
    public void setTravel(String travel) 
    {
        this.travel = travel;
    }

    public String getTravel() 
    {
        return travel;
    }
    public void setTemperature(String temperature) 
    {
        this.temperature = temperature;
    }

    public String getTemperature() 
    {
        return temperature;
    }
    public void setCough(String cough) 
    {
        this.cough = cough;
    }

    public String getCough() 
    {
        return cough;
    }
    public void setFever(String fever) 
    {
        this.fever = fever;
    }

    public String getFever() 
    {
        return fever;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("collegeName", getCollegeName())
            .append("studentClass", getStudentClass())
            .append("punchTime", getPunchTime())
            .append("tiimePart", getTiimePart())
            .append("travel", getTravel())
            .append("temperature", getTemperature())
            .append("cough", getCough())
            .append("fever", getFever())
            .toString();
    }
}
