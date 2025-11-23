package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.response.AssetBordMetricsVO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.business.service.OriginalAssetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.business.convert.AssetBoardConvert;
import com.ruoyi.web.controller.business.request.AssetBordRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    private AssetService         assetService;
    @Resource
    private OriginalAssetService originalAssetService;

    /**
     * 实物资产报表 概览
     */
    @PreAuthorize("@ss.hasPermi('board:physical:list')")
    @GetMapping("/physical/overview")
    public AjaxResult physicalOverview(@Valid @NotNull(message = "参数不能为空") AssetBordRequest request) {
        AssetBordReqBO assetBordReqBO = AssetBoardConvert.INSTANCE.toAssetBordReqBO(request);
        AssetBordMetricsVO respData = assetService.getPhysicalOverview(assetBordReqBO);
        return success(respData);
    }

    /**
     * 查询实物资产报表
     */
    @PreAuthorize("@ss.hasPermi('board:physical:list')")
    @GetMapping("/physical/metrics")
    public AjaxResult physicalBord(AssetBordRequest request) {

        return null;
    }

    /**
     * 查询账务资产报表
     */
    @PreAuthorize("@ss.hasPermi('board:ledger:list')")
    @GetMapping("/ledger/overview")
    public AjaxResult ledgerOverview(AssetBordRequest request) {
        AssetBordReqBO assetBordReqBO = AssetBoardConvert.INSTANCE.toAssetBordReqBO(request);
        AssetBordMetricsVO respData = originalAssetService.getLedgerOverview(assetBordReqBO);
        return success(respData);
    }

    /**
     * 查询账务资产报表
     */
    @PreAuthorize("@ss.hasPermi('board:ledger:list')")
    @GetMapping("/ledger/metrics")
    public AjaxResult ledgerBord(AssetBordRequest request) {

        return null;
    }

}
