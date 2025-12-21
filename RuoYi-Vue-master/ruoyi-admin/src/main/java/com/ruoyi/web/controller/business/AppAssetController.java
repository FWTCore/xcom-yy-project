package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.response.AssetDetailVO;
import com.ruoyi.business.domain.model.request.AssetCopyReqBO;
import com.ruoyi.business.service.AssetService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataDetail;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.business.convert.WebAssetConvert;
import com.ruoyi.web.controller.business.request.AssetCopyRequest;
import com.ruoyi.web.controller.business.request.AssetQueryRequest;
import com.ruoyi.web.controller.business.request.AssetUpsertRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

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
    private AssetService      assetService;
    @Resource
    private RedisLockRegistry redisLockRegistry;

    @ApiOperation("App-获取资产列表")
    @GetMapping(value = "/pageData")
    public TableDataDetail pageData(AssetQueryRequest request) {
        startPage();
        Asset asset = new Asset();
        asset.setProjectId(getProjectId());
        asset.setLocationId(request.getLocationId());
        asset.setSearchDeptId(request.getDeptId());
        asset.setSearchName(request.getSearchName());
        asset.setUsingDeptId(request.getUsingDeptId());
        asset.setCollectorUserId(request.getCollectorUserId());
        if (ObjectUtils.isNotEmpty(request.getStartTime())) {
            asset.getParams().put("beginTime", request.getStartTime());
        }
        if (ObjectUtils.isNotEmpty(request.getStartTime())) {
            asset.getParams().put("endTime", request.getStartTime());
        }
        List<AssetDetailVO> resp = assetService.selectAssetDetailList(asset);
        return getDataDetailTable(resp);
    }

    @ApiOperation("App-新增-编辑资产")
    @PostMapping(value = "/upset")
    @Log(title = "App-新增-编辑资产", businessType = BusinessType.UPDATE)
    public AjaxResult upset(@Validated @RequestBody AssetUpsertRequest request) {

        AssetDO assetDO = WebAssetConvert.INSTANCE.toAssetDO(request);
        assetDO.setDeptId(getProjectCompanyId());
        assetDO.setProjectId(getProjectId());
        Boolean resp = assetService.upsetAsset(assetDO) > 0;
        return success(resp);
    }

    @ApiOperation("App-复制资产")
    @PostMapping(value = "/copy")
    @Log(title = "App-复制资产", businessType = BusinessType.INSERT)
    public AjaxResult copy(@RequestBody @Validated AssetCopyRequest request) {
        if (request.getCopyNum().compareTo(0) <= 0 || request.getCopyNum().compareTo(150) > 0) {
            throw new ServiceException("复制数量区间显示0-150，请不要超过范围");
        }
        AssetCopyReqBO copyReqBO = new AssetCopyReqBO();
        copyReqBO.setId(request.getId());
        copyReqBO.setCopyNum(request.getCopyNum());
        copyReqBO.setDeptId(getProjectCompanyId());
        copyReqBO.setProjectId(getProjectId());
        Lock lock = redisLockRegistry.obtain("asset:copy:" + request.getId());
        try {
            if (!lock.tryLock()) {
                log.warn("资产id【" + request.getId() + "】复制时,未获取锁，资产正在被其他操作占用");
                throw new ServiceException("资产正在复制，请勿重复提交");
            }
            return success(assetService.copyData(copyReqBO));
        } catch (Exception e) {
            log.error("资产id【" + request.getId() + "】正在复制，请勿重复提交", e);
            return success(false);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取资产详细信息
     */
    @ApiOperation("App-扫码获取资产")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") String code) {
        if (StringUtils.isEmpty(code)) {
            throw new ServiceException("资产编码不能为空");
        }
        return success(assetService.selectAssetByCode(code, getProjectId()));
    }

}
