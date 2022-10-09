package com.campus.manage.service.impl;

import com.campus.manage.domain.CamHealth;
import com.campus.manage.mapper.CamHealthMapper;
import com.campus.manage.service.ICamHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 健康打卡Service业务层处理
 * 
 * @author ay
 * @date 2022-08-17
 */
@Service
public class CamHealthServiceImpl implements ICamHealthService 
{
    @Autowired
    private CamHealthMapper camHealthMapper;

    /**
     * 查询健康打卡
     * 
     * @param studentId 健康打卡主键
     * @return 健康打卡
     */
    @Override
    public CamHealth selectCamHealthByStudentId(Long studentId)
    {
        return camHealthMapper.selectCamHealthByStudentId(studentId);
    }

    /**
     * 查询健康打卡列表
     * 
     * @param camHealth 健康打卡
     * @return 健康打卡
     */
    @Override
    public List<CamHealth> selectCamHealthList(CamHealth camHealth)
    {
        return camHealthMapper.selectCamHealthList(camHealth);
    }

    /**
     * 查询健康打卡通过时间倒序
     * @param
     * @return
     */
    @Override
    public List<CamHealth> selectByStudentName(CamHealth camHealth) {
        return camHealthMapper.selectByStudentName(camHealth);
    }

    /**
     * 新增健康打卡
     * 
     * @param camHealth 健康打卡
     * @return 结果
     */
    @Override
    public int insertCamHealth(CamHealth camHealth)
    {
        return camHealthMapper.insertCamHealth(camHealth);
    }

    /**
     * 修改健康打卡
     * 
     * @param camHealth 健康打卡
     * @return 结果
     */
    @Override
    public int updateCamHealth(CamHealth camHealth)
    {
        return camHealthMapper.updateCamHealth(camHealth);
    }

    /**
     * 批量删除健康打卡
     * 
     * @param studentIds 需要删除的健康打卡主键
     * @return 结果
     */
    @Override
    public int deleteCamHealthByStudentIds(Long[] studentIds)
    {
        return camHealthMapper.deleteCamHealthByStudentIds(studentIds);
    }

    /**
     * 删除健康打卡信息
     * 
     * @param studentId 健康打卡主键
     * @return 结果
     */
    @Override
    public int deleteCamHealthByStudentId(Long studentId)
    {
        return camHealthMapper.deleteCamHealthByStudentId(studentId);
    }
}
