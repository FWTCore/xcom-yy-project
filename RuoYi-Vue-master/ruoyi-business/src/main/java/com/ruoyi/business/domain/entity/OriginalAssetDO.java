package com.ruoyi.business.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 原始资产对象 original_asset
 *
 * @author xcom
 * @date 2025/10/19
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OriginalAssetDO extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long   deptId;

    /**
     * 项目id,;
     */
    private Long   projectId;
    /**
     * 原始编码,;
     */
    @Excel(name = "原始编码")
    private String originalCode;

    /**
     * 分类id,;
     */
    private Long   categoryId;

    /**
     * 分类名称,;
     */
    @Excel(name = "门类名称")
    private String categoryName;

    /**
     * 品牌名称,;
     */
    @Excel(name = "品牌")
    private String brandName;

    /**
     * 资产名称,;
     */
    @Excel(name = "资产名称")
    private String assetName;

    /**
     * 规格型号,;
     */
    @Excel(name = "规格型号")
    private String specification;

    /**
     * 地点id,;
     */
    private Long   locationId;

    /**
     * 地点名称,;
     */
    @Excel(name = "地点名称")
    private String locationName;

    /**
     * 管理部门id,;
     */
    private Long   managedDeptId;

    /**
     * 管理部门名称,;
     */
    @Excel(name = "管理部门")
    private String managedDeptName;

    /**
     * 使用部门id,;
     */
    private Long   usingDeptId;

    /**
     * 使用部门名称,;
     */
    @Excel(name = "使用部门")
    private String usingDeptName;

    /**
     * 管理员工id,;
     */
    private Long   managedEmpId;

    /**
     * 管理员工名称,;
     */
    @Excel(name = "管理员工")
    private String managedEmpName;

    /**
     * 使用员工id,;
     */
    private Long   usingEmpId;

    /**
     * 使用员工名称,;
     */
    @Excel(name = "使用员工")
    private String usingEmpName;

    /**
     * 备注,;
     */
    @Excel(name = "备注")
    private String remark;
}
