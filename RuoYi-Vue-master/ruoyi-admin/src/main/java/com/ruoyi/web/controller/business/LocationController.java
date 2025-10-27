package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.model.Location;
import com.ruoyi.business.domain.model.response.LocationDetailVO;
import com.ruoyi.business.service.LocationService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 存放地点Controller
 *
 * @author xcom
 * @date 2025-10-03
 */
@Slf4j
@RestController
@Api(tags = "部门管理")
@RequestMapping("/location")
public class LocationController extends BaseController {
    @Resource
    private LocationService locationService;

    /**
     * 查询存放地点列表
     */
    @PreAuthorize("@ss.hasPermi('business:location:list')")
    @GetMapping("/list")
    public TableDataInfo list(Location location) {
        startPage();
        List<LocationDetailVO> list = locationService.selectLocationDetailList(location);
        return getDataTable(list);
    }

    /**
     * 导出存放地点列表
     */
    @PreAuthorize("@ss.hasPermi('business:location:export')")
    @Log(title = "存放地点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Location location) {
        List<LocationDetailVO> list = locationService.selectLocationDetailList(location);
        ExcelUtil<LocationDetailVO> util = new ExcelUtil<LocationDetailVO>(LocationDetailVO.class);
        util.exportExcel(response, list, "存放地点数据");
    }

    /**
     * 获取存放地点详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:location:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(locationService.selectLocationById(id));
    }

    /**
     * 新增存放地点
     */
    @PreAuthorize("@ss.hasPermi('business:location:add')")
    @Log(title = "存放地点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LocationDO location) {
        return toAjax(locationService.upsetData(location));
    }

    /**
     * 修改存放地点
     */
    @PreAuthorize("@ss.hasPermi('business:location:edit')")
    @Log(title = "存放地点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LocationDO location) {
        return toAjax(locationService.upsetData(location));
    }

    /**
     * 删除存放地点
     */
    @PreAuthorize("@ss.hasPermi('business:location:remove')")
    @Log(title = "存放地点", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(locationService.deleteLocationByIds(ids));
    }

    /**
     * 查询列表
     */
    @GetMapping("/listAll/{deptId}")
    public AjaxResult listAll(@PathVariable Long deptId) {
        Location location = new Location();
        location.setDeptId(deptId);
        List<LocationDO> list = locationService.selectLocationList(location);
        return success(list);
    }
}