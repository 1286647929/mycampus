package com.campus.manage.service.impl;

import java.util.List;
import com.campus.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campus.manage.mapper.CamCollegeMapper;
import com.campus.manage.domain.CamCollege;
import com.campus.manage.service.ICamCollegeService;

/**
 * 学院管理Service业务层处理
 * 
 * @author ay
 * @date 2022-08-17
 */
@Service
public class CamCollegeServiceImpl implements ICamCollegeService 
{
    @Autowired
    private CamCollegeMapper camCollegeMapper;

    /**
     * 查询学院管理
     * 
     * @param collegeId 学院管理主键
     * @return 学院管理
     */
    @Override
    public CamCollege selectCamCollegeByCollegeId(Long collegeId)
    {
        return camCollegeMapper.selectCamCollegeByCollegeId(collegeId);
    }

    /**
     * 查询学院管理列表
     * 
     * @param camCollege 学院管理
     * @return 学院管理
     */
    @Override
    public List<CamCollege> selectCamCollegeList(CamCollege camCollege)
    {
        return camCollegeMapper.selectCamCollegeList(camCollege);
    }

    /**
     * 新增学院管理
     * 
     * @param camCollege 学院管理
     * @return 结果
     */
    @Override
    public int insertCamCollege(CamCollege camCollege)
    {
        camCollege.setCreateTime(DateUtils.getNowDate());
        return camCollegeMapper.insertCamCollege(camCollege);
    }

    /**
     * 修改学院管理
     * 
     * @param camCollege 学院管理
     * @return 结果
     */
    @Override
    public int updateCamCollege(CamCollege camCollege)
    {
        camCollege.setUpdateTime(DateUtils.getNowDate());
        return camCollegeMapper.updateCamCollege(camCollege);
    }

    /**
     * 批量删除学院管理
     * 
     * @param collegeIds 需要删除的学院管理主键
     * @return 结果
     */
    @Override
    public int deleteCamCollegeByCollegeIds(Long[] collegeIds)
    {
        return camCollegeMapper.deleteCamCollegeByCollegeIds(collegeIds);
    }

    /**
     * 删除学院管理信息
     * 
     * @param collegeId 学院管理主键
     * @return 结果
     */
    @Override
    public int deleteCamCollegeByCollegeId(Long collegeId)
    {
        return camCollegeMapper.deleteCamCollegeByCollegeId(collegeId);
    }
}
