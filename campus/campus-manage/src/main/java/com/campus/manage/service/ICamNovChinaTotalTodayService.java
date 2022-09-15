package com.campus.manage.service;

import com.campus.manage.domain.CamNovChinaTotal;
import com.campus.manage.domain.CamNovChinaTotalToday;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/6 17:26
 */
public interface ICamNovChinaTotalTodayService {
    public CamNovChinaTotalToday selectCurrentChinaTotalToday();

    public int insertChinaTotalToday(CamNovChinaTotalToday camNovChinaTotalToday);
}
