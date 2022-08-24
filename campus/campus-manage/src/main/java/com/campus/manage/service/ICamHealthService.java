package com.campus.manage.service;

import java.util.List;
import com.campus.manage.domain.CamHealth;

/**
 * 健康打卡Service接口
 * 
 * @author ay
 * @date 2022-08-17
 */
public interface ICamHealthService 
{
    /**
     * 查询健康打卡
     * 
     * @param studentId 健康打卡主键
     * @return 健康打卡
     */
    public CamHealth selectCamHealthByStudentId(Long studentId);

    /**
     * 查询健康打卡列表
     * 
     * @param camHealth 健康打卡
     * @return 健康打卡集合
     */
    public List<CamHealth> selectCamHealthList(CamHealth camHealth);

    /**
     * 新增健康打卡
     * 
     * @param camHealth 健康打卡
     * @return 结果
     */
    public int insertCamHealth(CamHealth camHealth);

    /**
     * 修改健康打卡
     * 
     * @param camHealth 健康打卡
     * @return 结果
     */
    public int updateCamHealth(CamHealth camHealth);

    /**
     * 批量删除健康打卡
     * 
     * @param studentIds 需要删除的健康打卡主键集合
     * @return 结果
     */
    public int deleteCamHealthByStudentIds(Long[] studentIds);

    /**
     * 删除健康打卡信息
     * 
     * @param studentId 健康打卡主键
     * @return 结果
     */
    public int deleteCamHealthByStudentId(Long studentId);
}
