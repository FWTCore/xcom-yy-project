package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.DepartmentDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 部门;department数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Department extends DepartmentDO {

    private String       searchName;
    /**
     * 部门名称,;
     */
    private List<String> departmentNames;
}