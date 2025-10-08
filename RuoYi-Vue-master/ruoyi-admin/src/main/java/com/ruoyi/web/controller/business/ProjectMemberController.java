package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.entity.ProjectMemberDO;
import com.ruoyi.business.domain.model.ProjectMember;
import com.ruoyi.business.service.ProjectMemberService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 项目成员管理
 *
 * @author xcom
 * @date 2025/10/3
 */

@Slf4j
@RestController
@Api(tags = "项目成员管理")
@RequestMapping("/project-member")
public class ProjectMemberController extends BaseController {
    @Resource
    private ProjectMemberService projectMemberService;

    /**
     * 查询项目成员列表
     */
    @PreAuthorize("@ss.hasPermi('business:member:edit')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectMember projectMember) {
        startPage();
        List<ProjectMemberDO> list = projectMemberService.selectProjectMemberList(projectMember);
        return getDataTable(list);
    }

    /**
     * 查询项目成员列表
     */
    @PreAuthorize("@ss.hasPermi('business:member:edit')")
    @GetMapping("/unallocatedList")
    public TableDataInfo unallocatedList(ProjectMember projectMember) {
        startPage();
        List<SysUser> list = projectMemberService.selectUnallocatedList(projectMember);
        return getDataTable(list);
    }

    /**
     * 导出项目成员列表
     */
    @PreAuthorize("@ss.hasPermi('business:member:export')")
    @Log(title = "项目成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectMember projectMember) {
        List<ProjectMemberDO> list = projectMemberService.selectProjectMemberList(projectMember);
        ExcelUtil<ProjectMemberDO> util = new ExcelUtil<ProjectMemberDO>(ProjectMemberDO.class);
        util.exportExcel(response, list, "项目成员数据");
    }

    /**
     * 获取项目成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:member:edit')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(projectMemberService.selectProjectMemberById(id));
    }

    /**
     * 新增项目成员
     */
    @PreAuthorize("@ss.hasPermi('business:member:edit')")
    @Log(title = "项目成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectMemberDO projectMember) {
        return toAjax(projectMemberService.insertProjectMember(projectMember));
    }

    /**
     * 批量选择用户授权
     */
    @PreAuthorize("@ss.hasPermi('business:member:edit')")
    @Log(title = "项目成员管理", businessType = BusinessType.GRANT)
    @PutMapping("/authUser")
    public AjaxResult selectAuthUserAll(Long projectId, Long[] userIds) {
        return toAjax(projectMemberService.insertProjectMember(projectId, userIds));
    }

    /**
     * 设置项目负责人
     */
    @PreAuthorize("@ss.hasPermi('business:member:edit')")
    @Log(title = "设置/取消项目负责人", businessType = BusinessType.GRANT)
    @PutMapping("/setProjectLeader")
    public AjaxResult setProjectLeader(@RequestBody ProjectMember projectMember) {
        return toAjax(projectMemberService.setProjectLeader(projectMember));
    }

    /**
     * 修改项目成员
     */
    @PreAuthorize("@ss.hasPermi('business:member:edit')")
    @Log(title = "项目成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectMemberDO projectMember) {
        return toAjax(projectMemberService.updateProjectMember(projectMember));
    }

    /**
     * 删除项目成员
     */
    @PreAuthorize("@ss.hasPermi('business:member:remove')")
    @Log(title = "项目成员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(projectMemberService.deleteProjectMemberByIds(ids));
    }
}
