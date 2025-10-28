package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.response.CollectionStatsVO;
import com.ruoyi.business.domain.model.request.CollectionStatsReqBO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataDetail;
import com.ruoyi.web.controller.business.convert.WebCollectionStatsConvert;
import com.ruoyi.web.controller.business.request.CollectionStatsRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * App-采集统计
 *
 * @author xcom
 * @date 2025/10/26
 */

@Slf4j
@RestController
@Api(tags = "App-采集统计")
@RequestMapping("/base/m-collection")
public class AppCollectionStatsController extends BaseController {

    @Resource
    private AssetService assetService;

    @ApiOperation("App-获取维度采集统计")
    @GetMapping(value = "/pageStats")
    public TableDataDetail pageStats(CollectionStatsRequest request) {
        startPage();
        CollectionStatsReqBO reqBO = WebCollectionStatsConvert.INSTANCE.toCollectionStatsReqBO(request);
        reqBO.setProjectId(getProjectId());
        List<CollectionStatsVO> respData = assetService.selectCollectionStatsForDimension(reqBO);
        return getDataDetailTable(respData);
    }



}
