package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.AssetData;
import com.ruoyi.business.domain.model.request.AssetCheckBO;
import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.request.AssetCheckRelationalReqBO;
import com.ruoyi.business.domain.model.request.AssetCopyReqBO;
import com.ruoyi.business.domain.model.response.AssetDataDetailVO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.service.AssetCheckService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.web.controller.business.convert.AssetCheckConvert;
import com.ruoyi.web.controller.business.request.AssetCheckMetricsRequest;
import com.ruoyi.web.controller.business.request.AssetCheckRelationalRequest;
import com.ruoyi.web.controller.business.request.AssetCheckRequest;
import com.ruoyi.web.controller.business.request.AssetCopyRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Resource
    private AssetCheckService assetCheckService;

    /**
     * 指标列表
     */
//    @PreAuthorize("@ss.hasPermi('check:verification:list')")
    @GetMapping("/physical/metrics/list")
    public TableDataInfo listPhysicalMetrics(@Valid @NotNull(message = "参数不能为空") AssetCheckMetricsRequest request) {
        startPage();
        AssetCheckMetricsReqBO reqBO = AssetCheckConvert.INSTANCE.toAssetCheckMetricsReqBO(request);
        List<AssetMetricsVO> respData = assetCheckService.listPhysicalMetrics(reqBO);
        return getDataTable(respData);
    }

    /**
     * 查询实物资产列表
     */
//    @PreAuthorize("@ss.hasPermi('check:verification:list')")
    @GetMapping("/physical/list")
    public TableDataInfo listPhysical(@Valid @NotNull(message = "参数不能为空") AssetCheckRequest request) {
        startPage();
        AssetCheckBO assetCheckBO = AssetCheckConvert.INSTANCE.toAssetCheckBO(request);
        List<AssetDataDetailVO> respData = assetCheckService.listPhysicalForMysql(assetCheckBO);
        return getDataTable(respData);
    }

    /**
     * 指标列表
     */
//    @PreAuthorize("@ss.hasPermi('check:verification:list')")
    @GetMapping("/ledger/metrics/list")
    public TableDataInfo listLedgerMetrics(@Valid @NotNull(message = "参数不能为空") AssetCheckMetricsRequest request) {
        startPage();
        AssetCheckMetricsReqBO reqBO = AssetCheckConvert.INSTANCE.toAssetCheckMetricsReqBO(request);
        List<AssetMetricsVO> respData = assetCheckService.listLedgerMetrics(reqBO);
        return getDataTable(respData);
    }

    /**
     * 查询账务资产列表
     */
//    @PreAuthorize("@ss.hasPermi('check:verification:list')")
    @GetMapping("/ledger/list")
    public TableDataInfo listLedger(@Valid @NotNull(message = "参数不能为空") AssetCheckRequest request) {
        startPage();
        AssetCheckBO assetCheckBO = AssetCheckConvert.INSTANCE.toAssetCheckBO(request);
        List<AssetDataDetailVO> respData = assetCheckService.listLedgerForMysql(assetCheckBO);
        return getDataTable(respData);
    }

    @ApiOperation("资产关联")
    @PostMapping(value = "/relational")
    @Log(title = "资产关联", businessType = BusinessType.UPDATE)
    public AjaxResult relational(@RequestBody @Validated @NotNull AssetCheckRelationalRequest request) {
        AssetCheckRelationalReqBO reqBO = AssetCheckConvert.INSTANCE.toAssetCheckRelationalReqBO(request);
        return success(assetCheckService.relational(reqBO));
    }

}
