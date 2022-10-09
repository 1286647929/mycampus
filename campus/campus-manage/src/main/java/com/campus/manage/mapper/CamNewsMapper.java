package com.campus.manage.mapper;

import com.campus.manage.domain.CamNews;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 新闻Mapper接口
 * 
 * @author campus
 * @date 2022-09-24
 */
public interface CamNewsMapper 
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

    public int insertBatch(@Param("newsList") List<CamNews> newsList);

    /**
     * 修改新闻
     * 
     * @param camNews 新闻
     * @return 结果
     */
    public int updateCamNews(CamNews camNews);

    /**
     * 删除新闻
     * 
     * @param newId 新闻主键
     * @return 结果
     */
    public int deleteCamNewsByNewId(Long newId);

    /**
     * 批量删除新闻
     * 
     * @param newIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCamNewsByNewIds(Long[] newIds);
}
