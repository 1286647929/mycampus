package com.campus.manage.mapper;

import java.util.List;
import com.campus.manage.domain.CamCollege;

/**
 * 学院管理Mapper接口
 * 
 * @author ay
 * @date 2022-08-17
 */
public interface CamCollegeMapper 
{
    /**
     * 查询学院管理
     * 
     * @param collegeId 学院管理主键
     * @return 学院管理
     */
    public CamCollege selectCamCollegeByCollegeId(Long collegeId);

    /**
     * 查询学院管理列表
     * 
     * @param camCollege 学院管理
     * @return 学院管理集合
     */
    public List<CamCollege> selectCamCollegeList(CamCollege camCollege);

    /**
     * 新增学院管理
     * 
     * @param camCollege 学院管理
     * @return 结果
     */
    public int insertCamCollege(CamCollege camCollege);

    /**
     * 修改学院管理
     * 
     * @param camCollege 学院管理
     * @return 结果
     */
    public int updateCamCollege(CamCollege camCollege);

    /**
     * 删除学院管理
     * 
     * @param collegeId 学院管理主键
     * @return 结果
     */
    public int deleteCamCollegeByCollegeId(Long collegeId);

    /**
     * 批量删除学院管理
     * 
     * @param collegeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCamCollegeByCollegeIds(Long[] collegeIds);
}
