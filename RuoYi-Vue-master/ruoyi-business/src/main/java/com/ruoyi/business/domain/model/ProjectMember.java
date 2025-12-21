package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.ProjectMemberDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目成员;project_member数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectMember extends ProjectMemberDO {

    /**
     * 成员名称,;
     */
    private String userName;

}