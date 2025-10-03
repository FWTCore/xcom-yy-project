package com.ruoyi.web.controller.business;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.model.Employee;
import com.ruoyi.business.service.EmployeeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
 * 员工Controller
 *
 * @author xcom
 * @date 2025-10-03
 */
@Slf4j
@RestController
@Api(tags = "员工管理")
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
    @Resource
    private EmployeeService employeeService;

    /**
     * 查询员工列表
     */
    @PreAuthorize("@ss.hasPermi('business:employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(Employee employee) {
        startPage();
        List<EmployeeDO> list = employeeService.selectEmployeeList(employee);
        return getDataTable(list);
    }

    /**
     * 导出员工列表
     */
    @PreAuthorize("@ss.hasPermi('business:employee:export')")
    @Log(title = "员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Employee employee) {
        List<EmployeeDO> list = employeeService.selectEmployeeList(employee);
        ExcelUtil<EmployeeDO> util = new ExcelUtil<EmployeeDO>(EmployeeDO.class);
        util.exportExcel(response, list, "员工数据");
    }

    /**
     * 获取员工详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:employee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(employeeService.selectEmployeeById(id));
    }

    /**
     * 新增员工
     */
    @PreAuthorize("@ss.hasPermi('business:employee:add')")
    @Log(title = "员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmployeeDO employee) {
        return toAjax(employeeService.insertEmployee(employee));
    }

    /**
     * 修改员工
     */
    @PreAuthorize("@ss.hasPermi('business:employee:edit')")
    @Log(title = "员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmployeeDO employee) {
        return toAjax(employeeService.updateEmployee(employee));
    }

    /**
     * 删除员工
     */
    @PreAuthorize("@ss.hasPermi('business:employee:remove')")
    @Log(title = "员工", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(employeeService.deleteEmployeeByIds(ids));
    }
}