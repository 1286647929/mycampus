package com.campus.manage.controller;

import com.campus.common.annotation.Anonymous;
import com.campus.common.core.controller.BaseController;
import com.campus.common.core.domain.AjaxResult;
import com.campus.manage.domain.CamNovData;
import com.campus.manage.service.ICamNovDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 疫情地图34省份数据controller
 *
 * @author yyy
 * @date 2022/9/5 11:03
 */
@RestController
@RequestMapping("/campus/novdata")
@Anonymous  //匿名访问不鉴权注解
public class CamNovDataController extends BaseController {
    @Autowired
    private ICamNovDataService novDataService;

    @GetMapping("/list")
    public AjaxResult getAll(){
        List<CamNovData> list = novDataService.selectCamNovDataByIdDesc();
        return AjaxResult.success(list);
    }
}
