package com.campus.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.campus.common.annotation.Log;
import com.campus.common.core.controller.BaseController;
import com.campus.common.core.domain.AjaxResult;
import com.campus.common.enums.BusinessType;
import com.campus.manage.domain.CamCollege;
import com.campus.manage.service.ICamCollegeService;
import com.campus.common.utils.poi.ExcelUtil;
import com.campus.common.core.page.TableDataInfo;

/**
 * 学院管理Controller
 * 
 * @author ay
 * @date 2022-08-17
 */
@RestController
@RequestMapping("/campus/college")
public class CamCollegeController extends BaseController
{
    @Autowired
    private ICamCollegeService camCollegeService;

    /**
     * 查询学院管理列表
     */
    @PreAuthorize("@ss.hasPermi('campus:college:list')")
    @GetMapping("/list")
    public TableDataInfo list(CamCollege camCollege)
    {
        startPage();
        List<CamCollege> list = camCollegeService.selectCamCollegeList(camCollege);
        return getDataTable(list);
    }

    /**
     * 导出学院管理列表
     */
    @PreAuthorize("@ss.hasPermi('campus:college:export')")
    @Log(title = "学院管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CamCollege camCollege)
    {
        List<CamCollege> list = camCollegeService.selectCamCollegeList(camCollege);
        ExcelUtil<CamCollege> util = new ExcelUtil<CamCollege>(CamCollege.class);
        util.exportExcel(response, list, "学院管理数据");
    }

    /**
     * 获取学院管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('campus:college:query')")
    @GetMapping(value = "/{collegeId}")
    public AjaxResult getInfo(@PathVariable("collegeId") Long collegeId)
    {
        return AjaxResult.success(camCollegeService.selectCamCollegeByCollegeId(collegeId));
    }

    /**
     * 新增学院管理
     */
    @PreAuthorize("@ss.hasPermi('campus:college:add')")
    @Log(title = "学院管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CamCollege camCollege)
    {
        return toAjax(camCollegeService.insertCamCollege(camCollege));
    }

    /**
     * 修改学院管理
     */
    @PreAuthorize("@ss.hasPermi('campus:college:edit')")
    @Log(title = "学院管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CamCollege camCollege)
    {
        return toAjax(camCollegeService.updateCamCollege(camCollege));
    }

    /**
     * 删除学院管理
     */
    @PreAuthorize("@ss.hasPermi('campus:college:remove')")
    @Log(title = "学院管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{collegeIds}")
    public AjaxResult remove(@PathVariable Long[] collegeIds)
    {
        return toAjax(camCollegeService.deleteCamCollegeByCollegeIds(collegeIds));
    }
}
