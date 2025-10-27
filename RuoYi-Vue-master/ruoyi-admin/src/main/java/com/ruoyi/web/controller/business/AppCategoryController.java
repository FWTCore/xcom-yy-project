package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.model.Category;
import com.ruoyi.business.service.CategoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataDetail;
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
 * App-分类模块
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-分类模块")
@RequestMapping("/m/company/category")
public class AppCategoryController extends BaseController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation("App-获取分类列表")
    @GetMapping(value = "/pageData")
    public TableDataDetail pageData(SearchQueryRequest request) {
        startPage();
        Category category = new Category();
        category.setSearchName(request.getSearchName());
        List<CategoryDO> resp = categoryService.selectCategoryList(category);
        return getDataDetailTable(resp);
    }

}
