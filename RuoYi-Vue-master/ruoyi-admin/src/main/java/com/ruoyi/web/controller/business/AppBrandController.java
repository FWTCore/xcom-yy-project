package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.model.Brand;
import com.ruoyi.business.domain.model.BrandDetailVO;
import com.ruoyi.business.service.BrandService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataDetail;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.business.request.BrandQueryRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * App-品牌模块
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-品牌模块")
@RequestMapping("/m/company/brand")
public class AppBrandController extends BaseController {

    @Resource
    private BrandService brandService;

    @ApiOperation("App-获取品牌列表")
    @GetMapping(value = "/pageData")
    public TableDataDetail pageData(BrandQueryRequest request) {
        startPage();
        Brand brand = new Brand();
        brand.setCategoryId(request.getCategoryId());
        brand.setSearchName(request.getSearchName());
        List<BrandDetailVO> respData = brandService.selectBrandDetailList(brand);
        return getDataDetailTable(respData);
    }

}
