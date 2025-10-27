package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.ProjectDO;
import com.ruoyi.business.domain.model.Project;
import com.ruoyi.business.service.ProjectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataDetail;
import com.ruoyi.web.controller.business.request.SearchQueryRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * App-项目管理
 *
 * @author xcom
 * @date 2025/10/2
 */

@Slf4j
@RestController
@Api(tags = "App-项目管理")
@RequestMapping("/m/customer/project")
public class AppProjectController extends BaseController {

    @Resource
    private ProjectService projectService;

    @ApiOperation("App-获取项目列表")
    @GetMapping(value = "/pageData")
    public TableDataDetail pageData(SearchQueryRequest request) {
        startPage();
        Project project = new Project();
        project.setSearchName(request.getSearchName());
        List<ProjectDO> resp = projectService.selectProjectList(project);
        return getDataDetailTable(resp);
    }

}
