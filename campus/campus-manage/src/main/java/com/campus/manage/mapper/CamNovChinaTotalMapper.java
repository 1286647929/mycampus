package com.campus.manage.mapper;

import com.campus.manage.domain.CamNovChinaTotal;

import java.util.List;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/5 11:44
 */
public interface CamNovChinaTotalMapper {
    public CamNovChinaTotal selectCurrentChinaTotal();

    public int insertChinaTotal(CamNovChinaTotal camNovChinaTotal);
}
