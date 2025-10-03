package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.ProjectMemberDO;
import com.ruoyi.business.domain.model.ProjectMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目成员;(project_member)表数据库访问层
 *
 * @author :xcom
 * @date : 2025-9-18
 */
@Mapper
public interface ProjectMemberMapper {

    /**
     * 获取用户参与项目
     * @param userId
     * @return
     */
    List<ProjectMemberDO> listProjectMemberByUserId(@Param("userId") Long userId);

    /**
     * 查询项目成员
     *
     * @param id 项目成员主键
     * @return 项目成员
     */
    ProjectMemberDO selectProjectMemberById(Long id);

    /**
     * 查询项目成员
     *
     * @param ids 项目成员主键
     * @return 项目成员
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
     * 新增项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    int insertProjectMember(ProjectMemberDO projectMember);

    /**
     * 修改项目成员
     *
     * @param projectMember 项目成员
     * @return 结果
     */
    int updateProjectMember(ProjectMemberDO projectMember);

    /**
     * 删除项目成员
     *
     * @param id 项目成员主键
     * @return 结果
     */
    int deleteProjectMemberById(Long id);

    /**
     * 批量删除项目成员
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteProjectMemberByIds(Long[] ids);
}