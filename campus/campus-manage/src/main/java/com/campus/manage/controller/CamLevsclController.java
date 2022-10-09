package com.campus.manage.controller;

import com.campus.common.annotation.Log;
import com.campus.common.core.controller.BaseController;
import com.campus.common.core.domain.AjaxResult;
import com.campus.common.core.page.TableDataInfo;
import com.campus.common.enums.BusinessType;
import com.campus.common.utils.poi.ExcelUtil;
import com.campus.manage.domain.CamLevscl;
import com.campus.manage.service.ICamLevsclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * 离校管理Controller
 *
 * @author ay
 * @date 2022-08-17
 */
@RestController
@RequestMapping("/campus/levscl")
public class CamLevsclController extends BaseController {
    @Autowired
    private ICamLevsclService camLevsclService;

    /**
     * 查询离校管理列表
     */
    @PreAuthorize("@ss.hasPermi('campus:levscl:list')")
    @GetMapping("/list")
    public TableDataInfo list(CamLevscl camLevscl) throws ParseException {
        startPage();
        List<CamLevscl> list = camLevsclService.selectCamLevsclList(camLevscl);
        return getDataTable(list);
    }

    /**
     * 查询离校管理列表通过名称
     */
    @PreAuthorize("@ss.hasPermi('campus:levscl:list')")
    @GetMapping("/listByName")
    public TableDataInfo listByName(CamLevscl camLevscl) {
        startPage();
        List<CamLevscl> list = camLevsclService.selectByStudentNameList(camLevscl);
        return getDataTable(list);
    }

    /**
     * 导出离校管理列表
     */
    @PreAuthorize("@ss.hasPermi('campus:levscl:export')")
    @Log(title = "离校管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CamLevscl camLevscl) {
        List<CamLevscl> list = camLevsclService.selectCamLevsclList(camLevscl);
        ExcelUtil<CamLevscl> util = new ExcelUtil<CamLevscl>(CamLevscl.class);
        util.exportExcel(response, list, "离校管理数据");
    }

    /**
     * 获取离校管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('campus:levscl:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId) {
        return AjaxResult.success(camLevsclService.selectCamLevsclByStudentId(studentId));
    }

    /**
     * 新增离校管理
     */
    @PreAuthorize("@ss.hasPermi('campus:levscl:add')")
    @Log(title = "离校管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CamLevscl camLevscl) {
        return toAjax(camLevsclService.insertCamLevscl(camLevscl));
    }

    /**
     * 修改离校管理
     */
    @PreAuthorize("@ss.hasPermi('campus:levscl:edit')")
    @Log(title = "离校管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CamLevscl camLevscl) {
        return toAjax(camLevsclService.updateCamLevscl(camLevscl));
    }

    /**
     * 删除离校管理
     */
    @PreAuthorize("@ss.hasPermi('campus:levscl:remove')")
    @Log(title = "离校管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds) {
        return toAjax(camLevsclService.deleteCamLevsclByStudentIds(studentIds));
    }
}
