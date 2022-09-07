package com.campus.manage.service;

import com.campus.manage.domain.CamNovChinaTotal;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/6 17:26
 */
public interface ICamNovChinaTotalService {
    public CamNovChinaTotal selectCurrentChinaTotal();

    public int insertChinaTotal(CamNovChinaTotal camNovChinaTotal);
}
