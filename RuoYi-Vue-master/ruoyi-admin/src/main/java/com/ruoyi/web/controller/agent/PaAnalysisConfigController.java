package com.ruoyi.web.controller.agent;

import com.ruoyi.business.domain.entity.PaAnalysisConfigDO;
import com.ruoyi.business.domain.model.PaAnalysisConfig;
import com.ruoyi.business.service.PaAnalysisConfigService;
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
 * PA平台分析配置;PA平台分析配置Controller
 *
 * @author xcom
 * @date 2026/1/3
 */
@RestController
@RequestMapping("/business/config")
public class PaAnalysisConfigController extends BaseController {

    @Resource
    private PaAnalysisConfigService paAnalysisConfigService;

    /**
     * 查询PA平台分析配置;PA平台分析配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaAnalysisConfig paAnalysisConfig) {
        startPage();
        List<PaAnalysisConfigDO> list = paAnalysisConfigService.selectPaAnalysisConfigList(paAnalysisConfig);
        return getDataTable(list);
    }

    /**
     * 导出PA平台分析配置;PA平台分析配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:config:export')")
    @Log(title = "PA平台分析配置;PA平台分析配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaAnalysisConfig paAnalysisConfig) {
        List<PaAnalysisConfigDO> list = paAnalysisConfigService.selectPaAnalysisConfigList(paAnalysisConfig);
        ExcelUtil<PaAnalysisConfigDO> util = new ExcelUtil<PaAnalysisConfigDO>(PaAnalysisConfigDO.class);
        util.exportExcel(response, list, "PA平台分析配置;PA平台分析配置数据");
    }

    /**
     * 获取PA平台分析配置;PA平台分析配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(paAnalysisConfigService.selectPaAnalysisConfigById(id));
    }

    /**
     * 新增PA平台分析配置;PA平台分析配置
     */
   @PreAuthorize("@ss.hasPermi('system:config:add')")
    @Log(title = "PA平台分析配置;PA平台分析配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaAnalysisConfig paAnalysisConfig) {
        return toAjax(paAnalysisConfigService.insertPaAnalysisConfig(paAnalysisConfig));
    }

    /**
     * 修改PA平台分析配置;PA平台分析配置
     */
   @PreAuthorize("@ss.hasPermi('system:config:edit')")
    @Log(title = "PA平台分析配置;PA平台分析配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaAnalysisConfig paAnalysisConfig) {
        return toAjax(paAnalysisConfigService.updatePaAnalysisConfig(paAnalysisConfig));
    }

    /**
     * 删除PA平台分析配置;PA平台分析配置
     */
    @PreAuthorize("@ss.hasPermi('system:config:remove')")
    @Log(title = "PA平台分析配置;PA平台分析配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(paAnalysisConfigService.deletePaAnalysisConfigByIds(ids));
    }
}
