package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.ProjectDO;
import com.ruoyi.business.domain.entity.ProjectMemberDO;
import com.ruoyi.business.domain.model.ProjectMember;
import com.ruoyi.business.mapper.ProjectMemberMapper;
import com.ruoyi.business.service.ProjectMemberService;
import com.ruoyi.business.service.ProjectService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.service.ISysUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * ProjectMember Service
 *
 * @author xcom
 * @date 2025/9/23
 */

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Resource
    private ProjectMemberMapper projectMemberMapper;
    @Resource
    private ProjectService      projectService;
    @Resource
    private ISysUserService     userService;

    @Override
    public List<ProjectMemberDO> listProjectMemberByUserId(Long userId) {
        if (ObjectUtils.isEmpty(userId)) {
            return Lists.newArrayList();
        }
        return projectMemberMapper.listProjectMemberByUserId(userId);
    }

    /**
     * 查询项目成员
     *
     * @param id 项目成员主键
     * @return 项目成员
     */
    @Override
    public ProjectMemberDO selectProjectMemberById(Long id) {
        return projectMemberMapper.selectProjectMemberById(id);
    }

    /**
     * 查询项目成员
     *
     * @param ids 项目成员主键
     * @return 项目成员
     */
    @Override
    public List<ProjectMemberDO> selectProjectMemberByIds(List<Long> ids) {
        return projectMemberMapper.selectProjectMemberByIds(ids);
    }

    /**
     * 查询项目成员列表
     *
     * @param projectMember 项目成员
     * @return 项目成员
     */
    @Override
    @DataScope(deptAlias = "pm", projectAlias = "pm")
    public List<ProjectMemberDO> selectProjectMemberList(ProjectMember projectMember) {
        return projectMemberMapper.selectProjectMemberList(projectMember);
    }

    @Override
    @DataScope(deptAlias = "u", isUpgrade = true)
    public List<SysUser> selectUnallocatedList(ProjectMember projectMember) {
        return projectMemberMapper.selectUnallocatedList(projectMember);
    }

    /**
     * 新增项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    @Override
    public int insertProjectMember(ProjectMemberDO projectMember) {
        projectMember.setBaseFieldValue();
        return projectMemberMapper.insertProjectMember(projectMember);
    }

    @Override
    public int insertProjectMember(Long projectId, Long[] userIds) {
        ProjectDO projectDO = projectService.selectProjectById(projectId);
        if (ObjectUtils.isEmpty(projectDO)) {
            throw new ServiceException("项目数据不存在");
        }
        List<Long> userIdList = Arrays.asList(userIds);
        List<SysUser> sysUsers = userService.selectUserByIds(userIdList, 100L);
        if (CollectionUtils.isEmpty(sysUsers)) {
            throw new ServiceException("用户数据不存在");
        }
        Map<Long, SysUser> userMap = sysUsers.stream()
            .collect(toMap(SysUser::getUserId, Function.identity(), (k1, k2) -> k2));
        int resultData = 0;
        for (Long userId : userIdList) {
            if (!userMap.containsKey(userId)) {
                continue;
            }
            ProjectMember projectMember = new ProjectMember();
            projectMember.setProjectId(projectId);
            projectMember.setSystemUserId(userId);
            List<ProjectMemberDO> projectMemberList = this.selectProjectMemberList(projectMember);
            if (CollectionUtils.isNotEmpty(projectMemberList)) {
                continue;
            }
            SysUser sysUser = userMap.get(userId);
            ProjectMemberDO projectMemberDO = new ProjectMemberDO();
            projectMemberDO.setDeptId(projectDO.getDeptId());
            projectMemberDO.setProjectId(projectId);
            projectMemberDO.setSystemUserId(userId);
            projectMemberDO.setMemberName(sysUser.getNickName());
            projectMemberDO.setMemberPhone(sysUser.getPhonenumber());
            projectMemberDO.setMemberType(0);
            this.insertProjectMember(projectMemberDO);
            resultData++;
        }
        return resultData;
    }

    @Override
    public int setProjectLeader(ProjectMember projectMember) {

        ProjectMemberDO projectMemberDO = this.selectProjectMemberById(projectMember.getId());
        if (ObjectUtils.isEmpty(projectMemberDO)) {
            throw new ServiceException("用户数据不存在");
        }
        if (!projectMemberDO.getProjectId().equals(projectMember.getProjectId())) {
            throw new ServiceException("用户数据不存在");
        }
        if (projectMemberDO.getMemberType().equals(1)) {
            projectMemberDO.setMemberType(0);
        } else {
            ProjectMember projectMemberQuery = new ProjectMember();
            projectMemberQuery.setDeptId(projectMemberDO.getDeptId());
            projectMemberQuery.setProjectId(projectMemberDO.getProjectId());
            List<ProjectMemberDO> projectMemberDOS = this.selectProjectMemberList(projectMemberQuery);
            if (CollectionUtils.isNotEmpty(projectMemberDOS) && projectMemberDOS.stream()
                .anyMatch(e -> e.getMemberType().equals(1) && !e.getId().equals(projectMember.getId()))) {
                throw new ServiceException("该项目已存在项目负责人");
            }
            projectMemberDO.setMemberType(1);
        }
        return this.updateProjectMember(projectMemberDO);
    }

    /**
     * 修改项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    @Override
    public int updateProjectMember(ProjectMemberDO projectMember) {
        projectMember.setUpdatedFieldValue();
        return projectMemberMapper.updateProjectMember(projectMember);
    }

    /**
     * 批量删除项目成员
     *
     * @param ids 需要删除的项目成员主键
     * @return 结果
     */
    @Override
    public int deleteProjectMemberByIds(Long[] ids) {
        return projectMemberMapper.deleteProjectMemberByIds(ids);
    }

    /**
     * 删除项目成员信息
     *
     * @param id 项目成员主键
     * @return 结果
     */
    @Override
    public int deleteProjectMemberById(Long id) {
        return projectMemberMapper.deleteProjectMemberById(id);
    }
}
