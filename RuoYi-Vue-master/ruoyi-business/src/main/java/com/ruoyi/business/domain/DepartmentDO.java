package com.ruoyi.business.domain;


import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 部门;department数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DepartmentDO extends BaseEntityDO {


    /**
     * 系统公司id,;
     */
    private Long systemCompanyId;

    /**
     * 部门名称,;
     */
    private String departmentName;
}