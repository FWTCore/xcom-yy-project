package com.ruoyi.web.controller.business;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.business.model.response.OriginalAssetDetailVO;
import com.ruoyi.business.service.OriginalAssetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.business.convert.WebOriginalAssetConvert;
import com.ruoyi.web.controller.business.request.OriginalAssetImportRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 原始资产Controller
 *
 * @author xcom
 * @date 2025-10-19
 */
@Slf4j
@RestController
@Api(tags = "原始资产管理")
@RequestMapping("/originalAsset")
public class OriginalAssetController extends BaseController {

    @Resource
    private OriginalAssetService originalAssetService;

    /**
     * 查询原始资产列表
     */
    @PreAuthorize("@ss.hasPermi('business:original_asset:list')")
    @GetMapping("/list")
    public TableDataInfo list(OriginalAsset originalAsset) {
        startPage();
        List<OriginalAssetDetailVO> list = originalAssetService.selectOriginalAssetDetailList(originalAsset);
        return getDataTable(list);
    }

    /**
     * 导出原始资产列表
     */
    @PreAuthorize("@ss.hasPermi('business:original_asset:export')")
    @Log(title = "原始资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OriginalAsset originalAsset) {
        List<OriginalAssetDO> list = originalAssetService.selectOriginalAssetList(originalAsset);
        ExcelUtil<OriginalAssetDO> util = new ExcelUtil<OriginalAssetDO>(OriginalAssetDO.class);
        util.exportExcel(response, list, "原始资产数据");
    }

    /**
     * 获取原始资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:original_asset:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(originalAssetService.selectOriginalAssetById(id));
    }

    /**
     * 新增原始资产
     */
    @PreAuthorize("@ss.hasPermi('business:original_asset:add')")
    @Log(title = "原始资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody @Valid OriginalAssetDO originalAsset) {
        return toAjax(originalAssetService.insertOriginalAsset(originalAsset));
    }

    /**
     * 修改原始资产
     */
    @PreAuthorize("@ss.hasPermi('business:original_asset:edit')")
    @Log(title = "原始资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody @Valid OriginalAssetDO originalAsset) {
        return toAjax(originalAssetService.updateOriginalAsset(originalAsset));
    }

    /**
     * 删除原始资产
     */
    @PreAuthorize("@ss.hasPermi('business:original_asset:remove')")
    @Log(title = "原始资产", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(originalAssetService.deleteOriginalAssetByIds(ids));
    }

    @Log(title = "原始资产管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('business:original_asset:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<OriginalAssetImportRequest> util = new ExcelUtil<OriginalAssetImportRequest>(
            OriginalAssetImportRequest.class);
        List<OriginalAssetImportRequest> importList = util.importExcel(file.getInputStream());
        List<OriginalAsset> originalAssetList = WebOriginalAssetConvert.INSTANCE.toOriginalAsset(importList);
        String message = originalAssetService.importData(originalAssetList, updateSupport);
        return success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<OriginalAssetImportRequest> util = new ExcelUtil<OriginalAssetImportRequest>(
            OriginalAssetImportRequest.class);
        util.importTemplateExcel(response, "原始资产数据");
    }

}
