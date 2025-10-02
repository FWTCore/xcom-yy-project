package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.entity.EmployeeDO;
import com.ruoyi.business.domain.model.Employee;
import com.ruoyi.business.service.DepartmentService;
import com.ruoyi.business.service.EmployeeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.business.request.EmployeeUpsertRequest;
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
 * App-员工管理
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-员工管理")
@RequestMapping("/m/employee")
public class AppEmployeeController extends BaseController {

    @Resource
    private EmployeeService employeeService;

    @ApiOperation("App-获取员工列表")
    @PostMapping(value = "/pageData")
    public TableDataInfo pageData(SearchQueryRequest request) {
        Employee employee = new Employee();
        employee.setDeptId(getProjectCompanyId());
        employee.setSearchName(request.getSearchName());
        List<EmployeeDO> resp = employeeService.selectEmployeeList(employee);
        return getDataTable(resp);
    }

    @ApiOperation("App-新增/编辑员工")
    @PostMapping(value = "/upset")
    public AjaxResult upset(@RequestBody @Validated EmployeeUpsertRequest request) {
        EmployeeDO employeeDO = new EmployeeDO();
        employeeDO.setDeptId(getProjectCompanyId());
        employeeDO.setId(request.getId());
        employeeDO.setEmployeeName(request.getEmployeeName());
        return success(employeeService.upsetData(employeeDO));
    }

}
