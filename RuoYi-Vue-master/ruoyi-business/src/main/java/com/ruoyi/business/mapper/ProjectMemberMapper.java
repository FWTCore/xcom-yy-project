package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.ProjectMemberDO;
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
}