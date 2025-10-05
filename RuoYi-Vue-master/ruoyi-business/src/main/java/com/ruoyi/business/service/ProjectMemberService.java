package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.ProjectMemberDO;
import com.ruoyi.business.domain.model.ProjectMember;
import com.ruoyi.common.core.domain.entity.SysUser;

import java.util.List;

/**
 * ProjectMember Service
 *
 * @author xcom
 * @date 2025/9/23
 */

public interface ProjectMemberService {

    /**
     * 查找用户的所属项目
     * @param userId
     * @return
     */
    List<ProjectMemberDO> listProjectMemberByUserId(Long userId);

    /**
     * 查询项目成员
     *
     * @param id 项目成员主键
     * @return 项目成员
     */
    ProjectMemberDO selectProjectMemberById(Long id);

    /**
     * 查询分类
     *
     * @param ids  项目成员主键
     * @return 分类
     */
    List<ProjectMemberDO> selectProjectMemberByIds(List<Long> ids);

    /**
     * 查询项目成员列表
     *
     * @param projectMember 项目成员
     * @return 项目成员集合
     */
    List<ProjectMemberDO> selectProjectMemberList(ProjectMember projectMember);

    /**
     * 查询项目成员列表
     *
     * @param projectMember 项目成员
     * @return 项目成员集合
     */
    List<SysUser> selectUnallocatedList(ProjectMember projectMember);

    /**
     * 新增项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    int insertProjectMember(ProjectMemberDO projectMember);
    /**
     * 新增项目成员
     *
     * @param projectId
     * @param userIds
     * @return 结果
     */
    int insertProjectMember(Long projectId, Long[] userIds);

    /**
     * 修改项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    int updateProjectMember(ProjectMemberDO projectMember);

    /**
     * 批量删除项目成员
     *
     * @param ids 需要删除的项目成员主键集合
     * @return 结果
     */
    int deleteProjectMemberByIds(Long[] ids);

    /**
     * 删除项目成员信息
     *
     * @param id 项目成员主键
     * @return 结果
     */
    int deleteProjectMemberById(Long id);

}
