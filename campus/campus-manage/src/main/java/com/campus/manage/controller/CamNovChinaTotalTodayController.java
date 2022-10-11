package com.campus.manage.controller;

import com.campus.common.annotation.Anonymous;
import com.campus.common.core.controller.BaseController;
import com.campus.common.core.domain.AjaxResult;
import com.campus.manage.service.ICamNovChinaTotalTodayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 中国总体疫情——当天controller
 *
 * @author yyy
 * @date 2022/9/5 11:03
 */
@RestController
@RequestMapping("/campus/novchinatotaltoday")
@Anonymous  //开启匿名访问接口
public class CamNovChinaTotalTodayController extends BaseController {

    @Autowired
    private ICamNovChinaTotalTodayService novChinaTotalTodayService;

    @GetMapping("/list")
    public AjaxResult getNovChinaTotalTodayList(){
        return AjaxResult.success(novChinaTotalTodayService.selectCurrentChinaTotalToday());
    }
}
