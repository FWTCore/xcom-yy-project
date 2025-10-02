package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Category;
import com.ruoyi.business.domain.model.Material;
import com.ruoyi.business.domain.model.MaterialDetailVO;
import com.ruoyi.business.service.MaterialService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.business.request.SearchQueryRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * App-物资商品模块
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-物资商品模块")
@RequestMapping("/m/material")
public class AppMaterialController extends BaseController {

    @Resource
    private MaterialService materialService;

    @ApiOperation("App-获取物资商品列表")
    @PostMapping(value = "/pageData")
    public TableDataInfo pageData(SearchQueryRequest request) {
        startPage();
        Material material = new Material();
        material.setSearchName(request.getSearchName());
        List<MaterialDetailVO> resp = materialService.selectMaterialDetailList(material);
        return getDataTable(resp);
    }

    @ApiOperation("App-模糊查询物资名称列表")
    @PostMapping(value = "/pageSearchName")
    public TableDataInfo pageSearchName(SearchQueryRequest request) {
        startPage();
        Material material = new Material();
        material.setLeftSearchMaterialName(request.getSearchName());
        List<MaterialDetailVO> resp = materialService.selectMaterialDetailList(material);
        return getDataTable(resp);
    }

    @ApiOperation("App-模糊查询物资规格列表")
    @PostMapping(value = "/pageSearchSpec")
    public TableDataInfo pageSearchSpec(SearchQueryRequest request) {
        startPage();
        Material material = new Material();
        material.setLeftSearchSpecification(request.getSearchName());
        List<MaterialDetailVO> resp = materialService.selectMaterialDetailList(material);
        return getDataTable(resp);
    }

}
