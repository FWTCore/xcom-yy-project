package com.ruoyi.web.controller.business;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.business.domain.entity.BrandDO;
import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.model.Brand;
import com.ruoyi.business.domain.model.BrandDetailVO;
import com.ruoyi.business.domain.model.Category;
import com.ruoyi.business.service.BrandService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 品牌Controller
 *
 * @author xcom
 * @date 2025-10-03
 */
@Slf4j
@RestController
@Api(tags = "品牌管理")
@RequestMapping("/brand")
public class BrandController extends BaseController {
    @Resource
    private BrandService brandService;

    /**
     * 查询品牌列表
     */
    @PreAuthorize("@ss.hasPermi('material:brand:list')")
    @GetMapping("/list")
    public TableDataInfo list(Brand brand) {
        startPage();
        List<BrandDetailVO> list = brandService.selectBrandDetailList(brand);
        return getDataTable(list);
    }

    /**
     * 导出品牌列表
     */
    @PreAuthorize("@ss.hasPermi('material:brand:export')")
    @Log(title = "品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Brand brand) {
        List<BrandDO> list = brandService.selectBrandList(brand);
        ExcelUtil<BrandDO> util = new ExcelUtil<BrandDO>(BrandDO.class);
        util.exportExcel(response, list, "品牌数据");
    }

    /**
     * 获取品牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('material:brand:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(brandService.selectBrandById(id));
    }

    /**
     * 新增品牌
     */
    @PreAuthorize("@ss.hasPermi('material:brand:add')")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BrandDO brand) {
        return toAjax(brandService.insertBrand(brand));
    }

    /**
     * 修改品牌
     */
    @PreAuthorize("@ss.hasPermi('material:brand:edit')")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BrandDO brand) {
        return toAjax(brandService.updateBrand(brand));
    }

    /**
     * 删除品牌
     */
    @PreAuthorize("@ss.hasPermi('material:brand:remove')")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(brandService.deleteBrandByIds(ids));
    }

    /**
     * 查询品牌列表
     */
    @GetMapping("/listAll/{categoryId}")
    public AjaxResult listAll(@PathVariable Long categoryId) {
        Brand brand = new Brand();
        brand.setCategoryId(categoryId);
        List<BrandDO> list = brandService.selectBrandList(brand);
        return success(list);
    }
}