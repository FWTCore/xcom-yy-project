package com.ruoyi.web.controller.agent;

import com.ruoyi.business.domain.entity.PaBizOrchestrationDO;
import com.ruoyi.business.domain.model.PaBizOrchestration;
import com.ruoyi.business.service.PaBizOrchestrationService;
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
 * PA平台业务编排;PA平台业务编排Controller
 *
 * @author xcom
 * @date 2026/1/3
 */

@RestController
@RequestMapping("/business/orchestration")
public class PaBizOrchestrationController extends BaseController {
    @Resource
    private PaBizOrchestrationService paBizOrchestrationService;

    /**
     * 查询PA平台业务编排;PA平台业务编排列表
     */
    @PreAuthorize("@ss.hasPermi('system:orchestration:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaBizOrchestration paBizOrchestration) {
        startPage();
        List<PaBizOrchestrationDO> list = paBizOrchestrationService.selectPaBizOrchestrationList(paBizOrchestration);
        return getDataTable(list);
    }

    /**
     * 导出PA平台业务编排;PA平台业务编排列表
     */
    @PreAuthorize("@ss.hasPermi('system:orchestration:export')")
    @Log(title = "PA平台业务编排;PA平台业务编排", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaBizOrchestration paBizOrchestration) {
        List<PaBizOrchestrationDO> list = paBizOrchestrationService.selectPaBizOrchestrationList(paBizOrchestration);
        ExcelUtil<PaBizOrchestrationDO> util = new ExcelUtil<PaBizOrchestrationDO>(PaBizOrchestrationDO.class);
        util.exportExcel(response, list, "PA平台业务编排;PA平台业务编排数据");
    }

    /**
     * 获取PA平台业务编排;PA平台业务编排详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:orchestration:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(paBizOrchestrationService.selectPaBizOrchestrationById(id));
    }

    /**
     * 新增PA平台业务编排;PA平台业务编排
     */
    @PreAuthorize("@ss.hasPermi('system:orchestration:add')")
    @Log(title = "PA平台业务编排;PA平台业务编排", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaBizOrchestration paBizOrchestration) {
        return toAjax(paBizOrchestrationService.insertPaBizOrchestration(paBizOrchestration));
    }

    /**
     * 修改PA平台业务编排;PA平台业务编排
     */
    @PreAuthorize("@ss.hasPermi('system:orchestration:edit')")
    @Log(title = "PA平台业务编排;PA平台业务编排", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaBizOrchestration paBizOrchestration) {
        return toAjax(paBizOrchestrationService.updatePaBizOrchestration(paBizOrchestration));
    }

    /**
     * 删除PA平台业务编排;PA平台业务编排
     */
   @PreAuthorize("@ss.hasPermi('system:orchestration:remove')")
    @Log(title = "PA平台业务编排;PA平台业务编排", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(paBizOrchestrationService.deletePaBizOrchestrationByIds(ids));
    }
}
