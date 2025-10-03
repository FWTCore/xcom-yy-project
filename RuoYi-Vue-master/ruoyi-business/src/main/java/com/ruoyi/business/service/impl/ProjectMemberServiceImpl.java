package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.ProjectMemberDO;
import com.ruoyi.business.domain.model.ProjectMember;
import com.ruoyi.business.mapper.ProjectMemberMapper;
import com.ruoyi.business.service.ProjectMemberService;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<ProjectMemberDO> selectProjectMemberList(ProjectMember projectMember) {
        return projectMemberMapper.selectProjectMemberList(projectMember);
    }

    /**
     * 新增项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    @Override
    public int insertProjectMember(ProjectMemberDO projectMember) {
        return projectMemberMapper.insertProjectMember(projectMember);
    }

    /**
     * 修改项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    @Override
    public int updateProjectMember(ProjectMemberDO projectMember) {
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
