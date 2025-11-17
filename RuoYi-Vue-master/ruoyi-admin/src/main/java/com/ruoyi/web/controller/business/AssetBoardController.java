package com.ruoyi.web.controller.business;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.business.request.AssetBordRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AssetBoardController {
    /**
     * 查询实物资产报表
     */
    @PreAuthorize("@ss.hasPermi('board:physical:list')")
    @GetMapping("/physical/overview")
    public AjaxResult physicalOverview(AssetBordRequest request) {

        return null;
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

        return null;
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
