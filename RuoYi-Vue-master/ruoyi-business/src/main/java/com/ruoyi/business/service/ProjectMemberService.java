package com.ruoyi.business.service;

import com.ruoyi.business.domain.ProjectMemberDO;

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

}
