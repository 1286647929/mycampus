package com.campus.manage.service.impl;

import com.campus.manage.domain.CamLevscl;
import com.campus.manage.mapper.CamLevsclMapper;
import com.campus.manage.service.ICamLevsclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 离校管理Service业务层处理
 * 
 * @author ay
 * @date 2022-08-17
 */
@Service
public class CamLevsclServiceImpl implements ICamLevsclService 
{
    @Autowired
    private CamLevsclMapper camLevsclMapper;

    /**
     * 查询离校管理
     * 
     * @param studentId 离校管理主键
     * @return 离校管理
     */
    @Override
    public CamLevscl selectCamLevsclByStudentId(Long studentId)
    {
        return camLevsclMapper.selectCamLevsclByStudentId(studentId);
    }

    /**
     * 查询离校管理列表
     * 
     * @param camLevscl 离校管理
     * @return 离校管理
     */
    @Override
    public List<CamLevscl> selectCamLevsclList(CamLevscl camLevscl)
    {
        return camLevsclMapper.selectCamLevsclList(camLevscl);
    }

    /**
     * 查询离校管理列表通过名称
     *
     * @param camLevscl
     * @return
     */
    @Override
    public List<CamLevscl> selectByStudentNameList(CamLevscl camLevscl) {
        return camLevsclMapper.selectByStudentNameList(camLevscl);
    }

    /**
     * 新增离校管理
     * 
     * @param camLevscl 离校管理
     * @return 结果
     */
    @Override
    public int insertCamLevscl(CamLevscl camLevscl)
    {
        return camLevsclMapper.insertCamLevscl(camLevscl);
    }

    /**
     * 修改离校管理
     * 
     * @param camLevscl 离校管理
     * @return 结果
     */
    @Override
    public int updateCamLevscl(CamLevscl camLevscl)
    {
        return camLevsclMapper.updateCamLevscl(camLevscl);
    }

    /**
     * 批量删除离校管理
     * 
     * @param studentIds 需要删除的离校管理主键
     * @return 结果
     */
    @Override
    public int deleteCamLevsclByStudentIds(Long[] studentIds)
    {
        return camLevsclMapper.deleteCamLevsclByStudentIds(studentIds);
    }

    /**
     * 删除离校管理信息
     * 
     * @param studentId 离校管理主键
     * @return 结果
     */
    @Override
    public int deleteCamLevsclByStudentId(Long studentId)
    {
        return camLevsclMapper.deleteCamLevsclByStudentId(studentId);
    }
}
