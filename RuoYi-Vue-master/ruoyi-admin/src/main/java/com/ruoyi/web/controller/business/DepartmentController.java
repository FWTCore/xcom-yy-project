package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.entity.LocationDO;
import com.ruoyi.business.domain.model.Department;
import com.ruoyi.business.domain.model.DepartmentDetailVO;
import com.ruoyi.business.domain.model.Location;
import com.ruoyi.business.service.DepartmentService;
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
 * 部门Controller
 *
 * @author xcom
 * @date 2025-10-03
 */
@Slf4j
@RestController
@Api(tags = "部门管理")
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    @Resource
    private DepartmentService departmentService;

    /**
     * 查询部门列表
     */
    @PreAuthorize("@ss.hasPermi('business:department:list')")
    @GetMapping("/list")
    public TableDataInfo list(Department department) {
        startPage();
        List<DepartmentDetailVO> list = departmentService.selectDepartmentDetailList(department);
        return getDataTable(list);
    }

    /**
     * 导出部门列表
     */
    @PreAuthorize("@ss.hasPermi('business:department:export')")
    @Log(title = "部门", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Department department) {
        List<DepartmentDetailVO> list = departmentService.selectDepartmentDetailList(department);
        ExcelUtil<DepartmentDetailVO> util = new ExcelUtil<DepartmentDetailVO>(DepartmentDetailVO.class);
        util.exportExcel(response, list, "部门数据");
    }

    /**
     * 获取部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:department:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(departmentService.selectDepartmentById(id));
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@ss.hasPermi('business:department:add')")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DepartmentDO department) {
        return toAjax(departmentService.insertDepartment(department));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('business:department:edit')")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DepartmentDO department) {
        return toAjax(departmentService.updateDepartment(department));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@ss.hasPermi('business:department:remove')")
    @Log(title = "部门", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(departmentService.deleteDepartmentByIds(ids));
    }

    /**
     * 查询列表
     */
    @GetMapping("/listAll/{deptId}")
    public AjaxResult listAll(@PathVariable Long deptId) {
        Department department = new Department();
        department.setDeptId(deptId);
        List<DepartmentDO> list = departmentService.selectDepartmentList(department);
        return success(list);
    }
}