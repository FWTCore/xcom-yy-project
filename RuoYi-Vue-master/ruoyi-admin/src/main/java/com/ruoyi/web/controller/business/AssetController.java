package com.ruoyi.web.controller.business;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.model.request.AssetBatchUpdateReqBO;
import com.ruoyi.business.model.response.AssetDetailVO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.web.controller.business.convert.WebAssetConvert;
import com.ruoyi.web.controller.business.request.AssetBatchUpdateRequest;
import com.ruoyi.web.controller.utils.ImageUrlUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
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
 * 资产管理
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "资产管理")
@RequestMapping("/asset")
public class AssetController extends BaseController {
    @Resource
    private AssetService assetService;

    /**
     * 查询资产列表
     */
    @PreAuthorize("@ss.hasPermi('business:asset:list')")
    @GetMapping("/list")
    public TableDataInfo list(Asset asset) {
        startPage();
        List<AssetDetailVO> list = assetService.selectAssetDetailList(asset);
        if (CollectionUtils.isNotEmpty(list)) {
            for (AssetDetailVO data : list) {
                data.setMainImageUrl(ImageUrlUtil.paddedImageUrl(data.getMainImageUrl()));
                data.setImageUrl(ImageUrlUtil.paddedImageUrl(data.getImageUrl()));
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出资产列表
     */
    @PreAuthorize("@ss.hasPermi('business:asset:export')")
    @Log(title = "资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Asset asset) {
        List<AssetDetailVO> list = assetService.selectAssetDetailList(asset);
        ExcelUtil<AssetDetailVO> util = new ExcelUtil<AssetDetailVO>(AssetDetailVO.class);
        util.exportExcel(response, list, "资产数据");
    }

    /**
     * 获取资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:asset:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(assetService.selectAssetById(id));
    }

    /**
     * 新增资产
     */
    @PreAuthorize("@ss.hasPermi('business:asset:add')")
    @Log(title = "资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetDO asset) {
        if (ObjectUtils.isEmpty(asset.getDeptId()) || ObjectUtils.isEmpty(asset.getProjectId())) {
            throw new ServiceException("单位和项目不能为空");
        }
        return toAjax(assetService.upsetAsset(asset));
    }

    /**
     * 修改资产
     */
    @PreAuthorize("@ss.hasPermi('business:asset:edit')")
    @Log(title = "资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetDO asset) {
        if (ObjectUtils.isEmpty(asset.getId())) {
            throw new ServiceException("编辑数据id错误");
        }
        if (ObjectUtils.isEmpty(asset.getDeptId()) || ObjectUtils.isEmpty(asset.getProjectId())) {
            throw new ServiceException("单位和项目不能为空");
        }
        return toAjax(assetService.upsetAsset(asset));
    }

    /**
     * 删除资产
     */
    @PreAuthorize("@ss.hasPermi('business:asset:remove')")
    @Log(title = "资产", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(assetService.deleteAssetByIds(ids));
    }

    /**
     * 批量修改资产
     */
    @PreAuthorize("@ss.hasPermi('business:asset:edit')")
    @Log(title = "资产", businessType = BusinessType.UPDATE)
    @PutMapping("/batch")
    public AjaxResult batchEdit(@RequestBody @Valid AssetBatchUpdateRequest batchUpdateRequest) {
        AssetBatchUpdateReqBO batchUpdateReqBO = WebAssetConvert.INSTANCE.toAssetBatchUpdateReqBO(batchUpdateRequest);
        return toAjax(assetService.batchUpdate(batchUpdateReqBO));
    }
}
