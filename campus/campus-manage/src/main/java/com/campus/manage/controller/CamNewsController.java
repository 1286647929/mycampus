package com.campus.manage.controller;

import com.campus.common.annotation.Anonymous;
import com.campus.common.annotation.Log;
import com.campus.common.core.controller.BaseController;
import com.campus.common.core.domain.AjaxResult;
import com.campus.common.core.page.TableDataInfo;
import com.campus.common.enums.BusinessType;
import com.campus.common.utils.poi.ExcelUtil;
import com.campus.manage.domain.CamNews;
import com.campus.manage.service.ICamNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 新闻Controller
 * 
 * @author campus
 * @date 2022-09-24
 */
@RestController
@RequestMapping("/campus/news")
@Anonymous
public class CamNewsController extends BaseController
{
    @Autowired
    private ICamNewsService camNewsService;

    /**
     * 查询新闻列表
     */
    //@PreAuthorize("@ss.hasPermi('campus:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(CamNews camNews)
    {
        startPage();
        List<CamNews> list = camNewsService.selectCamNewsList(camNews);
        return getDataTable(list);
    }

    /**
     * 导出新闻列表
     */
    @PreAuthorize("@ss.hasPermi('campus:news:export')")
    @Log(title = "新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CamNews camNews)
    {
        List<CamNews> list = camNewsService.selectCamNewsList(camNews);
        ExcelUtil<CamNews> util = new ExcelUtil<CamNews>(CamNews.class);
        util.exportExcel(response, list, "新闻数据");
    }

    /**
     * 获取新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('campus:news:query')")
    @GetMapping(value = "/{newId}")
    public AjaxResult getInfo(@PathVariable("newId") Long newId)
    {
        return AjaxResult.success(camNewsService.selectCamNewsByNewId(newId));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('campus:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CamNews camNews)
    {
        return toAjax(camNewsService.insertCamNews(camNews));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('campus:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CamNews camNews)
    {
        return toAjax(camNewsService.updateCamNews(camNews));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('campus:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newIds}")
    public AjaxResult remove(@PathVariable Long[] newIds)
    {
        return toAjax(camNewsService.deleteCamNewsByNewIds(newIds));
    }
}
