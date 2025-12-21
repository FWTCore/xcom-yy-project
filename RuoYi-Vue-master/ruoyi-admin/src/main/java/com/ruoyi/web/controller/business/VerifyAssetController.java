package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.VerifyAssetDO;
import com.ruoyi.business.domain.model.VerifyAsset;
import com.ruoyi.business.domain.model.response.VerifyAssetDetailVO;
import com.ruoyi.business.service.VerifyAssetService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * 核实资产Controller
 *
 * @author xcom
 * @date 2025/12/21
 */

@Slf4j
@RestController
@Api(tags = "核实资产管理")
@RequestMapping("/verifyAsset")
public class VerifyAssetController extends BaseController {
    @Resource
    private VerifyAssetService verifyAssetService;

    /**
     * 查询核实资产列表
     */
    @PreAuthorize("@ss.hasPermi('business:verify:query')")
    @GetMapping("/list")
    public TableDataInfo list(VerifyAsset verifyAsset) {
        startPage();
        List<VerifyAssetDetailVO> list = verifyAssetService.selectVerifyAssetDetailList(verifyAsset);
        return getDataTable(list);
    }

    /**
     * 导出核实资产列表
     */
    @PreAuthorize("@ss.hasPermi('business:verify:export')")
    @Log(title = "核实资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VerifyAsset verifyAsset) {
        List<VerifyAssetDetailVO> list = verifyAssetService.selectVerifyAssetDetailList(verifyAsset);
        ExcelUtil<VerifyAssetDetailVO> util = new ExcelUtil<VerifyAssetDetailVO>(VerifyAssetDetailVO.class);
        util.exportExcel(response, list, "核实资产数据");
    }

    /**
     * 获取核实资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:verify:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(verifyAssetService.selectVerifyAssetById(id));
    }

    /**
     * 修改核实资产
     */
    @PreAuthorize("@ss.hasPermi('business:verify:edit')")
    @Log(title = "核实资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VerifyAsset verifyAsset) {
        return toAjax(verifyAssetService.updateVerifyAsset(verifyAsset));
    }
}
