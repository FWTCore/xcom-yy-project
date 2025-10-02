package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 项目;project数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long          deptId;

    /**
     * 项目名称,;
     */
    private String        projectName;

    /**
     * 项目开始周期,;
     */
    private LocalDateTime startDate;

    /**
     * 项目结束周期,;
     */
    private LocalDateTime endDate;

    /**
     * 项目状态,;
     */
    private Integer       projectStatus;

    /**
     * 备注,;
     */
    private String        remark;
}