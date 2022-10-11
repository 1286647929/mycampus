package com.campus.manage.controller;

import com.campus.common.annotation.Anonymous;
import com.campus.common.core.controller.BaseController;
import com.campus.common.core.domain.AjaxResult;
import com.campus.manage.service.ICamNovChinaTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 中国总体疫情controller
 *
 * @author yyy
 * @date 2022/9/5 11:03
 */
@RestController
@RequestMapping("/campus/novchinatotal")
@Anonymous  //开启匿名访问接口
public class CamNovChinaTotalController extends BaseController {

    @Autowired
    private ICamNovChinaTotalService novChinaTotalService;

    @GetMapping("/list")
    public AjaxResult getNovChinaTotalList(){
        return AjaxResult.success(novChinaTotalService.selectCurrentChinaTotal());
    }
}
