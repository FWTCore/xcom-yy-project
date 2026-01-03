package com.ruoyi.web.controller.agent;

import com.ruoyi.business.domain.entity.PaAnalysisConfigDetailDO;
import com.ruoyi.business.domain.model.PaAnalysisConfigDetail;
import com.ruoyi.business.service.PaAnalysisConfigDetailService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * PA平台分析配置详情;PA平台分析配置详情Controller
 *
 * @author xcom
 * @date 2026/1/3
 */
@RestController
@RequestMapping("/business/detail")
public class PaAnalysisConfigDetailController extends BaseController {
    @Resource
    private PaAnalysisConfigDetailService paAnalysisConfigDetailService;

    /**
     * 查询PA平台分析配置详情;PA平台分析配置详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaAnalysisConfigDetail paAnalysisConfigDetail) {
        startPage();
        List<PaAnalysisConfigDetailDO> list = paAnalysisConfigDetailService.selectPaAnalysisConfigDetailList(paAnalysisConfigDetail);
        return getDataTable(list);
    }

    /**
     * 导出PA平台分析配置详情;PA平台分析配置详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "PA平台分析配置详情;PA平台分析配置详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaAnalysisConfigDetail paAnalysisConfigDetail) {
        List<PaAnalysisConfigDetailDO> list = paAnalysisConfigDetailService.selectPaAnalysisConfigDetailList(paAnalysisConfigDetail);
        ExcelUtil<PaAnalysisConfigDetailDO> util = new ExcelUtil<PaAnalysisConfigDetailDO>(PaAnalysisConfigDetailDO.class);
        util.exportExcel(response, list, "PA平台分析配置详情;PA平台分析配置详情数据");
    }

    /**
     * 获取PA平台分析配置详情;PA平台分析配置详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(paAnalysisConfigDetailService.selectPaAnalysisConfigDetailById(id));
    }

    /**
     * 新增PA平台分析配置详情;PA平台分析配置详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "PA平台分析配置详情;PA平台分析配置详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaAnalysisConfigDetail paAnalysisConfigDetail) {
        return toAjax(paAnalysisConfigDetailService.insertPaAnalysisConfigDetail(paAnalysisConfigDetail));
    }

    /**
     * 修改PA平台分析配置详情;PA平台分析配置详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "PA平台分析配置详情;PA平台分析配置详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaAnalysisConfigDetail paAnalysisConfigDetail) {
        return toAjax(paAnalysisConfigDetailService.updatePaAnalysisConfigDetail(paAnalysisConfigDetail));
    }

    /**
     * 删除PA平台分析配置详情;PA平台分析配置详情
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "PA平台分析配置详情;PA平台分析配置详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(paAnalysisConfigDetailService.deletePaAnalysisConfigDetailByIds(ids));
    }
}
