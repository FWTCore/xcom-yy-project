package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.AssetDetailVO;
import com.ruoyi.business.model.request.AssetCopyReqBO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataDetail;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.web.controller.business.convert.WebAssetConvert;
import com.ruoyi.web.controller.business.request.AssetCopyRequest;
import com.ruoyi.web.controller.business.request.AssetQueryRequest;
import com.ruoyi.web.controller.business.request.AssetUpsertRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * App-资产管理
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-资产管理")
@RequestMapping("/m/customer/asset")
public class AppAssetController extends BaseController {

    @Resource
    private AssetService assetService;

    @ApiOperation("App-获取资产列表")
    @GetMapping(value = "/pageData")
    public TableDataDetail pageData(AssetQueryRequest request) {
        startPage();
        Asset asset = new Asset();
        asset.setProjectId(getProjectId());
        asset.setLocationId(request.getLocationId());
        asset.setSearchDeptId(request.getDeptId());
        asset.setSearchName(request.getSearchName());
        List<AssetDetailVO> resp = assetService.selectAssetDetailList(asset);
        return getDataDetailTable(resp);
    }

    @ApiOperation("App-新增/编辑资产")
    @PostMapping(value = "/upset")
    public AjaxResult upset(@Validated @RequestBody AssetUpsertRequest request) {

        AssetDO assetDO = WebAssetConvert.INSTANCE.toAssetDO(request);
        assetDO.setDeptId(getProjectCompanyId());
        assetDO.setProjectId(getProjectId());
        Boolean resp = assetService.upsetAsset(assetDO) > 0;
        return success(resp);
    }

    @ApiOperation("App-复制资产")
    @PostMapping(value = "/copy")
    public AjaxResult copy(@RequestBody @Validated AssetCopyRequest request) {
        if (request.getCopyNum().compareTo(0) <= 0 || request.getCopyNum().compareTo(20) > 0) {
            throw new ServiceException("复制数量区间显示0-20，请不要超过范围");
        }
        AssetCopyReqBO copyReqBO = new AssetCopyReqBO();
        copyReqBO.setId(request.getId());
        copyReqBO.setCopyNum(request.getCopyNum());
        copyReqBO.setDeptId(getProjectCompanyId());
        copyReqBO.setProjectId(getProjectId());
        return success(assetService.copyData(copyReqBO));
    }

}
