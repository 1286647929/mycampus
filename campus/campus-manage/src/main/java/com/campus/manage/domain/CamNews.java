package com.campus.manage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.campus.common.annotation.Excel;
import com.campus.common.core.domain.BaseEntity;

/**
 * 新闻对象 cam_news
 * 
 * @author campus
 * @date 2022-09-24
 */
public class CamNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻id */
    private Long newId;

    /** 标题 */
    @Excel(name = "标题")
    private String newTitle;

    /** 作者 */
    @Excel(name = "作者")
    private String newAuthor;

    /** 内容 */
    @Excel(name = "内容")
    private String newContent;

    /** 来源 */
    @Excel(name = "来源")
    private String newSource;

    /** 点击数 */
    @Excel(name = "点击数")
    private Long newHits;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String image;

    public void setNewId(Long newId) 
    {
        this.newId = newId;
    }

    public Long getNewId() 
    {
        return newId;
    }
    public void setNewTitle(String newTitle) 
    {
        this.newTitle = newTitle;
    }

    public String getNewTitle() 
    {
        return newTitle;
    }
    public void setNewAuthor(String newAuthor) 
    {
        this.newAuthor = newAuthor;
    }

    public String getNewAuthor() 
    {
        return newAuthor;
    }
    public void setNewContent(String newContent) 
    {
        this.newContent = newContent;
    }

    public String getNewContent() 
    {
        return newContent;
    }
    public void setNewSource(String newSource) 
    {
        this.newSource = newSource;
    }

    public String getNewSource() 
    {
        return newSource;
    }
    public void setNewHits(Long newHits) 
    {
        this.newHits = newHits;
    }

    public Long getNewHits() 
    {
        return newHits;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newId", getNewId())
            .append("newTitle", getNewTitle())
            .append("newAuthor", getNewAuthor())
            .append("newContent", getNewContent())
            .append("newSource", getNewSource())
            .append("newHits", getNewHits())
            .append("image", getImage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
