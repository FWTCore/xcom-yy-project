package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.ProjectMemberDO;
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
}
