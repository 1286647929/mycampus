package com.campus.manage.service;

import com.campus.manage.domain.CamNovData;
import java.util.List;


/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/5 11:06
 */
public interface ICamNovDataService {
    public List<CamNovData> selectCamNovDataByIdDesc();

    public int insertCamNovData(List<CamNovData> camNovData);
}
