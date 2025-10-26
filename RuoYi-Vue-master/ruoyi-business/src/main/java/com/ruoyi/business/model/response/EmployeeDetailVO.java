package com.ruoyi.business.model.response;

import com.ruoyi.business.domain.entity.EmployeeDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 员工明细
 *
 * @author xcom
 * @date 2025/10/5
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EmployeeDetailVO extends EmployeeDO {
    private String deptName;
}
