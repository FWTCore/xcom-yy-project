package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.model.Department;
import com.ruoyi.business.domain.model.Location;
import com.ruoyi.business.service.LocationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.business.request.LocationUpsertRequest;
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
 * App-存放地点管理
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-存放地点管理")
@RequestMapping("/m/location")
public class AppLocationController extends BaseController {
    @Resource
    private LocationService locationService;

    @ApiOperation("App-获取存放地点列表")
    @PostMapping(value = "/pageData")
    public TableDataInfo pageData(SearchQueryRequest request) {
        startPage();
        Location location = new Location();
        location.setDeptId(getProjectCompanyId());
        location.setSearchName(request.getSearchName());
        List<LocationDO> resp = locationService.selectLocationList(location);
        return getDataTable(resp);
    }

    @ApiOperation("App-新增/编辑存放地点")
    @PostMapping(value = "/upset")
    public AjaxResult upset(@RequestBody @Validated LocationUpsertRequest request) {
        LocationDO locationDO = new LocationDO();
        locationDO.setDeptId(getProjectCompanyId());
        locationDO.setId(request.getId());
        locationDO.setLocationName(request.getLocationName());
        return success(locationService.upsetData(locationDO));
    }

}
