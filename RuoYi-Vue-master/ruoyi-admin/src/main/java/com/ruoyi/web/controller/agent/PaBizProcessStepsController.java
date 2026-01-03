package com.ruoyi.web.controller.agent;

import com.ruoyi.business.domain.entity.PaBizProcessStepsDO;
import com.ruoyi.business.domain.model.PaBizProcessSteps;
import com.ruoyi.business.domain.model.response.PaBizProcessStepsVO;
import com.ruoyi.business.service.PaBizProcessStepsService;
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
 * PA平台业务办理步骤;PA平台业务办理步骤Controller
 *
 * @author xcom
 * @date 2026/1/3
 */

@RestController
@RequestMapping("/agent/steps")
public class PaBizProcessStepsController extends BaseController {
    @Resource
    private PaBizProcessStepsService paBizProcessStepsService;

    /**
     * 查询PA平台业务办理步骤;PA平台业务办理步骤列表
     */
    @PreAuthorize("@ss.hasPermi('system:steps:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaBizProcessSteps paBizProcessSteps) {
        startPage();
        List<PaBizProcessStepsVO> list = paBizProcessStepsService.selectPaBizProcessStepsList(paBizProcessSteps);
        return getDataTable(list);
    }

    /**
     * 导出PA平台业务办理步骤;PA平台业务办理步骤列表
     */
    @PreAuthorize("@ss.hasPermi('system:steps:export')")
    @Log(title = "PA平台业务办理步骤;PA平台业务办理步骤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaBizProcessSteps paBizProcessSteps) {
        List<PaBizProcessStepsVO> list = paBizProcessStepsService.selectPaBizProcessStepsList(paBizProcessSteps);
        ExcelUtil<PaBizProcessStepsVO> util = new ExcelUtil<PaBizProcessStepsVO>(PaBizProcessStepsVO.class);
        util.exportExcel(response, list, "PA平台业务办理步骤;PA平台业务办理步骤数据");
    }

    /**
     * 获取PA平台业务办理步骤;PA平台业务办理步骤详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:steps:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(paBizProcessStepsService.selectPaBizProcessStepsById(id));
    }

    /**
     * 新增PA平台业务办理步骤;PA平台业务办理步骤
     */
    @PreAuthorize("@ss.hasPermi('system:steps:add')")
    @Log(title = "PA平台业务办理步骤;PA平台业务办理步骤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaBizProcessSteps paBizProcessSteps) {
        return toAjax(paBizProcessStepsService.insertPaBizProcessSteps(paBizProcessSteps));
    }

    /**
     * 修改PA平台业务办理步骤;PA平台业务办理步骤
     */
    @PreAuthorize("@ss.hasPermi('system:steps:edit')")
    @Log(title = "PA平台业务办理步骤;PA平台业务办理步骤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaBizProcessSteps paBizProcessSteps) {
        return toAjax(paBizProcessStepsService.updatePaBizProcessSteps(paBizProcessSteps));
    }

    /**
     * 删除PA平台业务办理步骤;PA平台业务办理步骤
     */
    @PreAuthorize("@ss.hasPermi('system:steps:remove')")
    @Log(title = "PA平台业务办理步骤;PA平台业务办理步骤", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(paBizProcessStepsService.deletePaBizProcessStepsByIds(ids));
    }
}
