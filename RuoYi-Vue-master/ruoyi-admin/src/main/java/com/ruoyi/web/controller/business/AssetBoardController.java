package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.service.AssetDataService;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.business.service.MetricsService;
import com.ruoyi.business.service.OriginalAssetDataService;
import com.ruoyi.business.service.OriginalAssetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.web.controller.business.convert.AssetBoardConvert;
import com.ruoyi.web.controller.business.request.AssetBordRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 资产看板
 *
 * @author xcom
 * @date 2025/11/17
 */

@Slf4j
@RestController
@Api(tags = "资产看板")
@RequestMapping("/asset/board")
public class AssetBoardController extends BaseController {

    @Resource
    private AssetService             assetService;
    @Resource
    private OriginalAssetService     originalAssetService;
//    @Resource
//    private AssetDataService         assetDataService;
//    @Resource
//    private OriginalAssetDataService originalAssetDataService;
    @Resource
    private MetricsService           metricsService;

    /**
     * 实物资产报表 概览
     */
    @PreAuthorize("@ss.hasPermi('board:physical:list')")
    @GetMapping("/physical/overview")
    public AjaxResult physicalOverview(@Valid @NotNull(message = "参数不能为空") AssetBordRequest request) {
        AssetBordReqBO assetBordReqBO = AssetBoardConvert.INSTANCE.toAssetBordReqBO(request);
        AssetMetricsVO respData = assetService.getPhysicalOverview(assetBordReqBO);
        return success(respData);
    }

    /**
     * 查询实物资产报表
     */
    @PreAuthorize("@ss.hasPermi('board:physical:list')")
    @GetMapping("/physical/metrics")
    public AjaxResult physicalBord(@Valid @NotNull(message = "参数不能为空") AssetBordRequest request) {
        if (ObjectUtils.isEmpty(request.getMetricsType())) {
            throw new ServiceException("查询指标不能为空");
        }
        if (ObjectUtils.isEmpty(request.getOrderFlag())) {
            request.setOrderFlag(true);
        }
        AssetBordReqBO assetBordReqBO = AssetBoardConvert.INSTANCE.toAssetBordReqBO(request);
        List<AssetMetricsVO> respData = metricsService.listPhysicalBordMetrics(assetBordReqBO);
        //        List<AssetMetricsVO> respData = assetDataService.listPhysicalBord(assetBordReqBO);
        return success(respData);
    }

    /**
     * 查询账务资产报表
     */
    @PreAuthorize("@ss.hasPermi('board:ledger:list')")
    @GetMapping("/ledger/overview")
    public AjaxResult ledgerOverview(@Valid @NotNull(message = "参数不能为空") AssetBordRequest request) {
        AssetBordReqBO assetBordReqBO = AssetBoardConvert.INSTANCE.toAssetBordReqBO(request);
        AssetMetricsVO respData = originalAssetService.getLedgerOverview(assetBordReqBO);
        return success(respData);
    }

    /**
     * 查询账务资产报表
     */
    @PreAuthorize("@ss.hasPermi('board:ledger:list')")
    @GetMapping("/ledger/metrics")
    public AjaxResult ledgerBord(@Valid @NotNull(message = "参数不能为空") AssetBordRequest request) {
        if (ObjectUtils.isEmpty(request.getMetricsType())) {
            throw new ServiceException("查询指标不能为空");
        }
        if (ObjectUtils.isEmpty(request.getOrderFlag())) {
            request.setOrderFlag(true);
        }
        AssetBordReqBO assetBordReqBO = AssetBoardConvert.INSTANCE.toAssetBordReqBO(request);
        List<AssetMetricsVO> respData = metricsService.listLedgerBordMetrics(assetBordReqBO);
//        List<AssetMetricsVO> respData = originalAssetDataService.listLedgerBord(assetBordReqBO);
        return success(respData);
    }

}
