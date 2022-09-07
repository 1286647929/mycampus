package com.campus.manage.mapper;
import org.apache.ibatis.annotations.Param;

import com.campus.manage.domain.CamNovData;

import java.util.List;

/**
 * @author yyy
 * @version 1.0
 * @date 2022/9/5 11:07
 */
public interface CamNovDataMapper {
    public List<CamNovData> selectCamNovDataByIdDesc();

    public int insertBatch(@Param("camNovDataCollection") List<CamNovData> camNovDataList);
}
