package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.HomeAssetStatsVO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * App-账户管理
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-账户管理")
@RequestMapping("/m/account")
public class AppHomeController extends BaseController {

    @Resource
    private AssetService assetService;

    @ApiOperation("App-获取资产统计")
    @GetMapping(value = "/getAssetStats")
    public AjaxResult getAssetStats() {
        HomeAssetStatsVO respData = assetService.getHomeAssetStats(getProjectId());
        return success(respData);
    }

}
