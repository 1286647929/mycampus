package com.campus.manage.service;

import com.campus.manage.domain.CamLevscl;

import java.util.List;

/**
 * 离校管理Service接口
 * 
 * @author ay
 * @date 2022-08-17
 */
public interface ICamLevsclService 
{
    /**
     * 查询离校管理
     * 
     * @param studentId 离校管理主键
     * @return 离校管理
     */
    public CamLevscl selectCamLevsclByStudentId(Long studentId);

    /**
     * 查询离校管理列表
     * 
     * @param camLevscl 离校管理
     * @return 离校管理集合
     */
    public List<CamLevscl> selectCamLevsclList(CamLevscl camLevscl);

    /**
     * 查询离校管理列表通过名称
     * @param camLevscl
     * @return
     */
    public List<CamLevscl> selectByStudentNameList(CamLevscl camLevscl);

    /**
     * 新增离校管理
     * 
     * @param camLevscl 离校管理
     * @return 结果
     */
    public int insertCamLevscl(CamLevscl camLevscl);

    /**
     * 修改离校管理
     * 
     * @param camLevscl 离校管理
     * @return 结果
     */
    public int updateCamLevscl(CamLevscl camLevscl);

    /**
     * 批量删除离校管理
     * 
     * @param studentIds 需要删除的离校管理主键集合
     * @return 结果
     */
    public int deleteCamLevsclByStudentIds(Long[] studentIds);

    /**
     * 删除离校管理信息
     * 
     * @param studentId 离校管理主键
     * @return 结果
     */
    public int deleteCamLevsclByStudentId(Long studentId);
}
