package com.ruoyi.web.controller.business;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Material;
import com.ruoyi.business.domain.model.response.MaterialDetailVO;
import com.ruoyi.business.service.MaterialService;
import com.ruoyi.web.controller.utils.ImageUrlUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * 物资 Controller
 *
 * @author xcom
 * @date 2025-10-03
 */

@Slf4j
@RestController
@Api(tags = "物资管理")
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @Resource
    private MaterialService materialService;

    /**
     * 查询物资列表
     */
    @PreAuthorize("@ss.hasPermi('material:product:query')")
    @GetMapping("/list")
    public TableDataInfo list(Material material) {
        startPage();
        List<MaterialDetailVO> list = materialService.selectMaterialDetailList(material);
        if (CollectionUtils.isNotEmpty(list)) {
            for (MaterialDetailVO data : list) {
                data.setMainImageUrl(ImageUrlUtil.paddedImageUrl(data.getMainImageUrl()));
                data.setImageUrl(ImageUrlUtil.paddedImageUrl(data.getImageUrl()));
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出物资列表
     */
    @PreAuthorize("@ss.hasPermi('material:product:export')")
    @Log(title = "物资", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Material material) {
        List<MaterialDetailVO> list = materialService.selectMaterialDetailList(material);
        ExcelUtil<MaterialDetailVO> util = new ExcelUtil<MaterialDetailVO>(MaterialDetailVO.class);
        util.exportExcel(response, list, "物资数据");
    }

    /**
     * 获取物资详细信息
     */
    @PreAuthorize("@ss.hasPermi('material:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(materialService.selectMaterialById(id));
    }

    /**
     * 新增物资
     */
    @PreAuthorize("@ss.hasPermi('material:product:add')")
    @Log(title = "物资", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaterialDO material) {
        return toAjax(materialService.insertMaterial(material));
    }

    /**
     * 修改物资
     */
    @PreAuthorize("@ss.hasPermi('material:product:edit')")
    @Log(title = "物资", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaterialDO material) {
        return toAjax(materialService.updateMaterial(material));
    }

    /**
     * 删除物资
     */
    @PreAuthorize("@ss.hasPermi('material:product:remove')")
    @Log(title = "物资", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(materialService.deleteMaterialByIds(ids));
    }
}