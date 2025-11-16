package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
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
@RequestMapping("/asset/bord")
public class AssetBordController {

    /**
     * 查询实物资产报表
     */
    @PreAuthorize("@ss.hasPermi('business:asset:list')")
    @GetMapping("/physical/bord")
    public AjaxResult physicalBord(Asset asset) {

        return null;
    }

    /**
     * 查询实物资产报表
     */
    @PreAuthorize("@ss.hasPermi('business:asset:list')")
    @GetMapping("/ledger/bord")
    public AjaxResult ledgerBord(Asset asset) {

        return null;
    }



}
