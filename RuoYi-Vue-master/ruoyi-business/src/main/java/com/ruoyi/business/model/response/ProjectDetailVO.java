package com.ruoyi.business.model.response;

import com.ruoyi.business.domain.entity.ProjectDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目明细
 *
 * @author xcom
 * @date 2025/10/5
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectDetailVO extends ProjectDO {
    private String deptName;
}
