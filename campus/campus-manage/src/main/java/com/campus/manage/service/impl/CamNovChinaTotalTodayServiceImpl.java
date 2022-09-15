package com.campus.manage.service.impl;

import com.campus.manage.domain.CamNovChinaTotal;
import com.campus.manage.domain.CamNovChinaTotalToday;
import com.campus.manage.mapper.CamNovChinaTotalTodayMapper;
import com.campus.manage.service.ICamNovChinaTotalTodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/6 17:26
 */
@Service
public class CamNovChinaTotalTodayServiceImpl implements ICamNovChinaTotalTodayService {

    @Autowired
    private CamNovChinaTotalTodayMapper chinaTotalTodayMapper;


    @Override
    public CamNovChinaTotalToday selectCurrentChinaTotalToday() {
        return chinaTotalTodayMapper.selectCurrentChinaTotalToday();
    }

    @Override
    public int insertChinaTotalToday(CamNovChinaTotalToday camNovChinaTotalToday) {
        return chinaTotalTodayMapper.insertChinaTotalToday(camNovChinaTotalToday);
    }
}
