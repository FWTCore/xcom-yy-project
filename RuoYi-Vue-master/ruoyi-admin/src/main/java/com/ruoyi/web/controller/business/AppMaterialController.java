package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.Material;
import com.ruoyi.business.domain.model.response.MaterialDetailVO;
import com.ruoyi.business.service.MaterialService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataDetail;
import com.ruoyi.web.controller.business.request.MaterialNameQueryRequest;
import com.ruoyi.web.controller.business.request.MaterialSpecQueryRequest;
import com.ruoyi.web.controller.business.request.SearchQueryRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/m/company/material")
public class AppMaterialController extends BaseController {

    @Resource
    private MaterialService materialService;

    @ApiOperation("App-获取物资商品列表")
    @GetMapping(value = "/pageData")
    public TableDataDetail pageData(SearchQueryRequest request) {
        startPage();
        Material material = new Material();
        material.setSearchName(request.getSearchName());
        List<MaterialDetailVO> resp = materialService.selectMaterialDetailList(material);
        return getDataDetailTable(resp);
    }

    @ApiOperation("App-模糊查询物资名称列表")
    @GetMapping(value = "/pageSearchName")
    public TableDataDetail pageSearchName(MaterialNameQueryRequest request) {
        startPage();
        Material material = new Material();
        material.setCategoryId(request.getCategoryId());
        material.setBrandName(request.getBrandName());
        material.setLeftSearchMaterialName(request.getSearchName());
        List<MaterialDetailVO> resp = materialService.selectMaterialDetailList(material);
        return getDataDetailTable(resp);
    }

    @ApiOperation("App-模糊查询物资规格列表")
    @GetMapping(value = "/pageSearchSpec")
    public TableDataDetail pageSearchSpec(MaterialSpecQueryRequest request) {
        startPage();
        Material material = new Material();
        material.setCategoryId(request.getCategoryId());
        material.setBrandName(request.getBrandName());
        material.setMaterialName(request.getMaterialName());
        material.setLeftSearchSpecification(request.getSearchName());
        List<MaterialDetailVO> resp = materialService.selectMaterialDetailList(material);
        return getDataDetailTable(resp);
    }

}
