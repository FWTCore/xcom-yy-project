package com.ruoyi.web.controller.agent;

import com.ruoyi.business.domain.entity.PaBizProcessStepsFileDO;
import com.ruoyi.business.domain.model.PaBizProcessStepsFile;
import com.ruoyi.business.service.PaBizProcessStepsFileService;
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
 * PA平台业务办理步骤文件;PA平台业务办理步骤文件Controller
 *
 * @author xcom
 * @date 2026/1/3
 */

@RestController
@RequestMapping("/agent/file")
public class PaBizProcessStepsFileController extends BaseController {
    @Resource
    private PaBizProcessStepsFileService paBizProcessStepsFileService;

    /**
     * 查询PA平台业务办理步骤文件;PA平台业务办理步骤文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaBizProcessStepsFile paBizProcessStepsFile) {
        startPage();
        List<PaBizProcessStepsFileDO> list = paBizProcessStepsFileService.selectPaBizProcessStepsFileList(paBizProcessStepsFile);
        return getDataTable(list);
    }

    /**
     * 导出PA平台业务办理步骤文件;PA平台业务办理步骤文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:file:export')")
    @Log(title = "PA平台业务办理步骤文件;PA平台业务办理步骤文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaBizProcessStepsFile paBizProcessStepsFile) {
        List<PaBizProcessStepsFileDO> list = paBizProcessStepsFileService.selectPaBizProcessStepsFileList(paBizProcessStepsFile);
        ExcelUtil<PaBizProcessStepsFileDO> util = new ExcelUtil<PaBizProcessStepsFileDO>(PaBizProcessStepsFileDO.class);
        util.exportExcel(response, list, "PA平台业务办理步骤文件;PA平台业务办理步骤文件数据");
    }

    /**
     * 获取PA平台业务办理步骤文件;PA平台业务办理步骤文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:file:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(paBizProcessStepsFileService.selectPaBizProcessStepsFileById(id));
    }

    /**
     * 新增PA平台业务办理步骤文件;PA平台业务办理步骤文件
     */
    @PreAuthorize("@ss.hasPermi('system:file:add')")
    @Log(title = "PA平台业务办理步骤文件;PA平台业务办理步骤文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaBizProcessStepsFile paBizProcessStepsFile) {
        return toAjax(paBizProcessStepsFileService.insertPaBizProcessStepsFile(paBizProcessStepsFile));
    }

    /**
     * 修改PA平台业务办理步骤文件;PA平台业务办理步骤文件
     */
    @PreAuthorize("@ss.hasPermi('system:file:edit')")
    @Log(title = "PA平台业务办理步骤文件;PA平台业务办理步骤文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaBizProcessStepsFile paBizProcessStepsFile) {
        return toAjax(paBizProcessStepsFileService.updatePaBizProcessStepsFile(paBizProcessStepsFile));
    }

    /**
     * 删除PA平台业务办理步骤文件;PA平台业务办理步骤文件
     */
    @PreAuthorize("@ss.hasPermi('system:file:remove')")
    @Log(title = "PA平台业务办理步骤文件;PA平台业务办理步骤文件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(paBizProcessStepsFileService.deletePaBizProcessStepsFileByIds(ids));
    }
}
