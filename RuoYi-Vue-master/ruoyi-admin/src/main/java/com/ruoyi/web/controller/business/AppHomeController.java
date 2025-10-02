package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.vo.HomeAssetStatsVO;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-账户管理")
@RequestMapping("/m/account")
public class AppHomeController extends BaseController {



    @ApiOperation("App-获取资产统计")
    @GetMapping(value = "/getAssetStats")
    public AjaxResult getAssetStats() {

        HomeAssetStatsVO respData = new HomeAssetStatsVO();
        return success(respData);
    }

}
