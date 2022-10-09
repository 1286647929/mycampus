package com.campus.manage.service.impl;

import com.campus.common.utils.DateUtils;
import com.campus.manage.domain.CamNews;
import com.campus.manage.mapper.CamNewsMapper;
import com.campus.manage.service.ICamNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新闻Service业务层处理
 * 
 * @author campus
 * @date 2022-09-24
 */
@Service
public class CamNewsServiceImpl implements ICamNewsService
{
    @Autowired
    private CamNewsMapper camNewsMapper;

    /**
     * 查询新闻
     * 
     * @param newId 新闻主键
     * @return 新闻
     */
    @Override
    public CamNews selectCamNewsByNewId(Long newId)
    {
        return camNewsMapper.selectCamNewsByNewId(newId);
    }

    /**
     * 查询新闻列表
     * 
     * @param camNews 新闻
     * @return 新闻
     */
    @Override
    public List<CamNews> selectCamNewsList(CamNews camNews)
    {
        return camNewsMapper.selectCamNewsList(camNews);
    }

    /**
     * 新增新闻
     * 
     * @param camNews 新闻
     * @return 结果
     */
    @Override
    public int insertCamNews(CamNews camNews)
    {
        camNews.setCreateTime(DateUtils.getNowDate());
        return camNewsMapper.insertCamNews(camNews);
    }

    /**
     * 批量新增
     * @param camNews
     * @return
     */
    @Override
    public int insertBatch(List<CamNews> camNews) {
        return camNewsMapper.insertBatch(camNews);
    }


    /**
     * 修改新闻
     * 
     * @param camNews 新闻
     * @return 结果
     */
    @Override
    public int updateCamNews(CamNews camNews)
    {
        camNews.setUpdateTime(DateUtils.getNowDate());
        return camNewsMapper.updateCamNews(camNews);
    }

    /**
     * 批量删除新闻
     * 
     * @param newIds 需要删除的新闻主键
     * @return 结果
     */
    @Override
    public int deleteCamNewsByNewIds(Long[] newIds)
    {
        return camNewsMapper.deleteCamNewsByNewIds(newIds);
    }

    /**
     * 删除新闻信息
     * 
     * @param newId 新闻主键
     * @return 结果
     */
    @Override
    public int deleteCamNewsByNewId(Long newId)
    {
        return camNewsMapper.deleteCamNewsByNewId(newId);
    }
}
