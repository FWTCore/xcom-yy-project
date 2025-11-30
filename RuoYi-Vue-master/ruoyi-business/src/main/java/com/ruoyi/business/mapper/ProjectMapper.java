package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.ProjectDO;
import com.ruoyi.business.domain.model.Project;
import com.ruoyi.business.domain.model.response.ProjectDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 项目;(project)表数据库访问层
 * @author :xcom
 * @date : 2025-9-18
 */
@Mapper
public interface ProjectMapper {

    /**
     * 查询项目
     *
     * @param id 项目主键
     * @return 项目
     */
    ProjectDetailVO selectProjectById(Long id);

    /**
     * 查询项目
     *
     * @param ids 项目主键
     * @return 项目
     */
    List<ProjectDO> selectProjectByIds(List<Long> ids);

    /**
     * 查询项目列表
     *
     * @param project 项目
     * @return 项目集合
     */
    List<ProjectDO> selectProjectList(Project project);

    /**
     * 查询项目列表
     *
     * @param project 项目
     * @return 项目集合
     */
    List<ProjectDetailVO> selectProjectDetailList(Project project);

    /**
     * 新增项目
     *
     * @param project 项目
     * @return 结果
     */
    int insertProject(ProjectDO project);

    /**
     * 修改项目
     *
     * @param project 项目
     * @return 结果
     */
    int updateProject(ProjectDO project);

    /**
     * 删除项目
     *
     * @param id 项目主键
     * @return 结果
     */
    int deleteProjectById(Long id);

    /**
     * 批量删除项目
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteProjectByIds(Long[] ids);

    /**
     * 更改项目状态
     * @param projectId
     * @return
     */
    int changeProjectStatusForProgress(@Param("projectId") Long projectId);

    /**
     * 更改项目状态
     * @param projectId
     * @return
     */
    int changeProjectStatusForCompleted(@Param("projectId") Long projectId);
}