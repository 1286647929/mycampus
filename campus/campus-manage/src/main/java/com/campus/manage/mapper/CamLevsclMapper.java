package com.campus.manage.mapper;

import java.util.List;
import com.campus.manage.domain.CamLevscl;

/**
 * 离校管理Mapper接口
 * 
 * @author ay
 * @date 2022-08-17
 */
public interface CamLevsclMapper 
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
     * 删除离校管理
     * 
     * @param studentId 离校管理主键
     * @return 结果
     */
    public int deleteCamLevsclByStudentId(Long studentId);

    /**
     * 批量删除离校管理
     * 
     * @param studentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCamLevsclByStudentIds(Long[] studentIds);
}
