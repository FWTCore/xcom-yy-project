package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 员工;employee数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Employee extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long         deptId;

    /**
     * 员工名称,;
     */
    private String       employeeName;
    private String       searchName;
    /**
     * 员工名称,;
     */
    private List<String> employeeNames;
}