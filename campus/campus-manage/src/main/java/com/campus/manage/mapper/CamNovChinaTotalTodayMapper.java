package com.campus.manage.mapper;

import com.campus.manage.domain.CamNovChinaTotal;
import com.campus.manage.domain.CamNovChinaTotalToday;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/5 11:44
 */
public interface CamNovChinaTotalTodayMapper {
    public CamNovChinaTotalToday selectCurrentChinaTotalToday();

    public int insertChinaTotalToday(CamNovChinaTotalToday camNovChinaTotalToday);
}
