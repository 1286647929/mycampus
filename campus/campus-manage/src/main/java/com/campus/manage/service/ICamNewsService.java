package com.campus.manage.service;

import com.campus.manage.domain.CamNews;

import java.util.List;

/**
 * 新闻Service接口
 * 
 * @author campus
 * @date 2022-09-24
 */
public interface ICamNewsService 
{
    /**
     * 查询新闻
     * 
     * @param newId 新闻主键
     * @return 新闻
     */
    public CamNews selectCamNewsByNewId(Long newId);

    /**
     * 查询新闻列表
     * 
     * @param camNews 新闻
     * @return 新闻集合
     */
    public List<CamNews> selectCamNewsList(CamNews camNews);

    /**
     * 新增新闻
     * 
     * @param camNews 新闻
     * @return 结果
     */
    public int insertCamNews(CamNews camNews);

    /**
     * 批量新增
     * @param camNews
     * @return
     */
    public int insertBatch(List<CamNews> camNews);

    /**
     * 修改新闻
     * 
     * @param camNews 新闻
     * @return 结果
     */
    public int updateCamNews(CamNews camNews);

    /**
     * 批量删除新闻
     * 
     * @param newIds 需要删除的新闻主键集合
     * @return 结果
     */
    public int deleteCamNewsByNewIds(Long[] newIds);

    /**
     * 删除新闻信息
     * 
     * @param newId 新闻主键
     * @return 结果
     */
    public int deleteCamNewsByNewId(Long newId);
}
