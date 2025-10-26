package com.ruoyi.business.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 原始资产对象 original_asset
 *
 * @author xcom
 * @date 2025/10/19
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OriginalAsset extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long          deptId;
    /**
    * 部门名称,;
    */
    private String        deptName;

    /**
     * 项目id,;
     */
    private Long          projectId;
    /**
     * 项目名称,;
     */
    private String        projectName;
    /**
     * 原始编码,;
     */
    private String        originalCode;

    /**
     * 原始编码,;
     */
    private String        searchOriginalCode;

    /**
     * 分类id,;
     */
    private Long          categoryId;

    /**
     * 分类名称,;
     */
    private String        categoryName;

    /**
     * 品牌名称,;
     */
    private String        brandName;
    private String        searchBrandName;

    /**
     * 资产名称,;
     */
    private String        assetName;
    private String        searchAssetName;

    /**
     * 规格型号,;
     */
    private String        specification;
    private String        searchSpecification;

    /**
     * 地点id,;
     */
    private Long          locationId;

    /**
     * 地点名称,;
     */
    private String        locationName;
    private String        searchLocationName;

    /**
     * 管理部门id,;
     */
    private Long          managedDeptId;

    /**
     * 管理部门名称,;
     */
    private String        managedDeptName;
    private String        searchManagedDeptName;

    /**
     * 使用部门id,;
     */
    private Long          usingDeptId;

    /**
     * 使用部门名称,;
     */
    private String        usingDeptName;
    private String        searchUsingDeptName;

    /**
     * 管理员工id,;
     */
    private Long          managedEmpId;

    /**
     * 管理员工名称,;
     */
    private String        managedEmpName;
    private String        searchManagedEmpName;

    /**
     * 使用员工id,;
     */
    private Long          usingEmpId;

    /**
     * 使用员工名称,;
     */
    private String        usingEmpName;
    private String        searchUsingEmpName;

    /**
     * 备注,;
     */
    private String        remark;
    /**
     * 获得时间,;
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime obtainTime;
    /**
     * 价值,;
     */
    private BigDecimal    productPrice;
}
