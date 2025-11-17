package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.business.request.AssetCheckRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 资产核对
 *
 * @author xcom
 * @date 2025/11/17
 */

@Slf4j
@RestController
@Api(tags = "资产核对")
@RequestMapping("/asset/check")
public class AssetCheckController extends BaseController {

    /**
     * 指标列表
     */
    @PreAuthorize("@ss.hasPermi('check:verification:list')")
    @GetMapping("/metrics/list")
    public TableDataInfo listMetrics(AssetCheckRequest request) {
        startPage();

        return getDataTable(null);
    }

    /**
     * 查询实物资产列表
     */
    @PreAuthorize("@ss.hasPermi('check:verification:list')")
    @GetMapping("/physical/list")
    public TableDataInfo listPhysical(AssetCheckRequest request) {
        startPage();

        return getDataTable(null);
    }

    /**
     * 查询账务资产列表
     */
    @PreAuthorize("@ss.hasPermi('check:verification:list')")
    @GetMapping("/ledger/list")
    public TableDataInfo listLedger(AssetCheckRequest request) {
        startPage();

        return getDataTable(null);
    }

}
