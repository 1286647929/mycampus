package com.campus.manage.service.impl;

import com.campus.manage.domain.CamNovData;
import com.campus.manage.mapper.CamNovDataMapper;
import com.campus.manage.service.ICamNovDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/5 11:07
 */
@Service
public class CamNovDataServiceImpl implements ICamNovDataService {

    @Autowired
    private CamNovDataMapper camNovDataMapper;

    @Override
    public List<CamNovData> selectCamNovDataByIdDesc() {
        return camNovDataMapper.selectCamNovDataByIdDesc();
    }

    @Override
    public int insertCamNovData(List<CamNovData> camNovData) {
        return camNovDataMapper.insertBatch(camNovData);
    }
}
