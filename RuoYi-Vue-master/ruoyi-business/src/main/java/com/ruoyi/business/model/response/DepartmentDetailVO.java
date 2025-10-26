package com.ruoyi.business.model.response;

import com.ruoyi.business.domain.entity.DepartmentDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 部门明细
 *
 * @author xcom
 * @date 2025/10/5
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DepartmentDetailVO extends DepartmentDO {
    private String deptName;
}
