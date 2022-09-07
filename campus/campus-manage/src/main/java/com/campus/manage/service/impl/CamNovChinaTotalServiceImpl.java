package com.campus.manage.service.impl;

import com.campus.manage.domain.CamNovChinaTotal;
import com.campus.manage.mapper.CamNovChinaTotalMapper;
import com.campus.manage.service.ICamNovChinaTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/6 17:26
 */
@Service
public class CamNovChinaTotalServiceImpl implements ICamNovChinaTotalService {

    @Autowired
    private CamNovChinaTotalMapper chinaTotalMapper;

    @Override
    public CamNovChinaTotal selectCurrentChinaTotal() {
        return chinaTotalMapper.selectCurrentChinaTotal();
    }

    @Override
    public int insertChinaTotal(CamNovChinaTotal camNovChinaTotal) {
        return chinaTotalMapper.insertChinaTotal(camNovChinaTotal);
    }
}
