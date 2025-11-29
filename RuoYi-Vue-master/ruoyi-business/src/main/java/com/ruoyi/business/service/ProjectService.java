package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.ProjectDO;
import com.ruoyi.business.domain.model.Project;
import com.ruoyi.business.domain.model.response.ProjectDetailVO;

import java.util.List;

/**
 * project sercie
 *
 * @author xcom
 * @date 2025/9/20
 */

public interface ProjectService {
    /**
     * 查询项目
     *
     * @param id 项目主键
     * @return 项目
     */
    ProjectDetailVO selectProjectById(Long id);

    /**
     * 查询分类
     *
     * @param ids  项目主键
     * @return 分类
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
     * 批量删除项目
     *
     * @param ids 需要删除的项目主键集合
     * @return 结果
     */
    int deleteProjectByIds(Long[] ids);

    /**
     * 删除项目信息
     *
     * @param id 项目主键
     * @return 结果
     */
    int deleteProjectById(Long id);

    /**
     * 变更项目状态
     * @param projectId
     * @return
     */
    void changeProjectStatus(Long projectId);
}
