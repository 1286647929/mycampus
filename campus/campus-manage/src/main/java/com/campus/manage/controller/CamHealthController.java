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
import com.campus.manage.domain.CamHealth;
import com.campus.manage.service.ICamHealthService;
import com.campus.common.utils.poi.ExcelUtil;
import com.campus.common.core.page.TableDataInfo;

/**
 * 健康打卡Controller
 * 
 * @author ay
 * @date 2022-08-17
 */
@RestController
@RequestMapping("/campus/health")
public class CamHealthController extends BaseController
{
    @Autowired
    private ICamHealthService camHealthService;

    /**
     * 查询健康打卡列表
     */
    @PreAuthorize("@ss.hasPermi('campus:health:list')")
    @GetMapping("/list")
    public TableDataInfo list(CamHealth camHealth)
    {
        startPage();
        List<CamHealth> list = camHealthService.selectCamHealthList(camHealth);
        return getDataTable(list);
    }

    /**
     * 导出健康打卡列表
     */
    @PreAuthorize("@ss.hasPermi('campus:health:export')")
    @Log(title = "健康打卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CamHealth camHealth)
    {
        List<CamHealth> list = camHealthService.selectCamHealthList(camHealth);
        ExcelUtil<CamHealth> util = new ExcelUtil<CamHealth>(CamHealth.class);
        util.exportExcel(response, list, "健康打卡数据");
    }

    /**
     * 获取健康打卡详细信息
     */
    @PreAuthorize("@ss.hasPermi('campus:health:query')")
    @GetMapping(value = "/{studentId}")
    public AjaxResult getInfo(@PathVariable("studentId") Long studentId)
    {
        return AjaxResult.success(camHealthService.selectCamHealthByStudentId(studentId));
    }

    /**
     * 新增健康打卡
     */
    @PreAuthorize("@ss.hasPermi('campus:health:add')")
    @Log(title = "健康打卡", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CamHealth camHealth)
    {
        return toAjax(camHealthService.insertCamHealth(camHealth));
    }

    /**
     * 修改健康打卡
     */
    @PreAuthorize("@ss.hasPermi('campus:health:edit')")
    @Log(title = "健康打卡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CamHealth camHealth)
    {
        return toAjax(camHealthService.updateCamHealth(camHealth));
    }

    /**
     * 删除健康打卡
     */
    @PreAuthorize("@ss.hasPermi('campus:health:remove')")
    @Log(title = "健康打卡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{studentIds}")
    public AjaxResult remove(@PathVariable Long[] studentIds)
    {
        return toAjax(camHealthService.deleteCamHealthByStudentIds(studentIds));
    }
}
