package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
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
public class ProjectMember extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long    deptId;

    /**
     * 项目id,;
     */
    private Long    projectId;

    /**
     * 系统用户id,;
     */
    private Long    systemUserId;

    /**
     * 成员名称,;
     */
    private String  memberName;

    /**
     * 成员类型,;
     */
    private Integer memberType;

    /**
     * 成员手机号,;
     */
    private String  memberPhone;
}