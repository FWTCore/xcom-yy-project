package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.ProjectDO;
import com.ruoyi.business.domain.model.Project;
import com.ruoyi.business.mapper.ProjectMapper;
import com.ruoyi.business.service.ProjectService;
import com.ruoyi.common.annotation.DataScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Project Service
 *
 * @author xcom
 * @date 2025/9/20
 */

@Service
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectMapper projectMapper;

    /**
     * 查询项目
     *
     * @param id 项目主键
     * @return 项目
     */
    @Override
    public ProjectDO selectProjectById(Long id) {
        return projectMapper.selectProjectById(id);
    }

    /**
     * 查询项目
     *
     * @param ids 项目主键
     * @return 项目
     */
    @Override
    public List<ProjectDO> selectProjectByIds(List<Long> ids) {
        return projectMapper.selectProjectByIds(ids);
    }

    /**
     * 查询项目列表
     *
     * @param project 项目
     * @return 项目
     */
    @Override
    @DataScope(deptAlias = "p", projectAlias = "p", isSelfTable = true)
    public List<ProjectDO> selectProjectList(Project project) {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目
     *
     * @param project 项目
     * @return 结果
     */
    @Override
    public int insertProject(ProjectDO project) {
        project.setBaseFieldValue();
        return projectMapper.insertProject(project);
    }

    /**
     * 修改项目
     *
     * @param project 项目
     * @return 结果
     */
    @Override
    public int updateProject(ProjectDO project) {
        project.setUpdatedFieldValue();
        return projectMapper.updateProject(project);
    }

    /**
     * 批量删除项目
     *
     * @param ids 需要删除的项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectByIds(Long[] ids) {
        return projectMapper.deleteProjectByIds(ids);
    }

    /**
     * 删除项目信息
     *
     * @param id 项目主键
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long id) {
        return projectMapper.deleteProjectById(id);
    }
}
