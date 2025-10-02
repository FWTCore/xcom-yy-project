package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.DepartmentDO;
import com.ruoyi.business.domain.model.Department;
import com.ruoyi.business.service.DepartmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.web.controller.business.request.DepartmentUpsertRequest;
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
 * App-部门管理
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-部门管理")
@RequestMapping("/m/department")
public class AppDepartmentController extends BaseController {

    @Resource
    private DepartmentService departmentService;

    @ApiOperation("App-获取部门列表")
    @PostMapping(value = "/pageData")
    public TableDataInfo pageData(SearchQueryRequest request) {
        startPage();
        Department department = new Department();
        department.setDeptId(getProjectCompanyId());
        department.setSearchName(request.getSearchName());
        List<DepartmentDO> resp = departmentService.selectDepartmentList(department);
        return getDataTable(resp);
    }

    @ApiOperation("App-新增/编辑部门")
    @PostMapping(value = "/upset")
    public AjaxResult upset(@RequestBody @Validated DepartmentUpsertRequest request) {
        DepartmentDO departmentDO = new DepartmentDO();
        departmentDO.setDeptId(getProjectCompanyId());
        departmentDO.setDepartmentName(request.getDepartmentName());
        departmentDO.setId(request.getId());
        return success(departmentService.upsetData(departmentDO));
    }

}
