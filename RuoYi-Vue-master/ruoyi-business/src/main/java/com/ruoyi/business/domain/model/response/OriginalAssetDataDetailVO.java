package com.ruoyi.business.domain.model.response;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * OriginalAssetDataDetailVO
 *
 * @author xcom
 * @date 2025/11/29
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OriginalAssetDataDetailVO extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long          deptId;

    /**
     * 项目id,;
     */
    private Long          projectId;
    /**
     * 原始编码,;
     */
    private String        originalCode;

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

    /**
     * 资产名称,;
     */
    private String        assetName;

    /**
     * 规格型号,;
     */
    private String        specification;

    /**
     * 地点id,;
     */
    private Long          locationId;

    /**
     * 地点名称,;
     */
    private String        locationName;

    /**
     * 管理部门id,;
     */
    private Long          managedDeptId;

    /**
     * 管理部门名称,;
     */
    private String        managedDeptName;

    /**
     * 使用部门id,;
     */
    private Long          usingDeptId;

    /**
     * 使用部门名称,;
     */
    private String        usingDeptName;

    /**
     * 管理员工id,;
     */
    private Long          managedEmpId;

    /**
     * 管理员工名称,;
     */
    private String        managedEmpName;

    /**
     * 使用员工id,;
     */
    private Long          usingEmpId;

    /**
     * 使用员工名称,;
     */
    private String        usingEmpName;

    /**
     * 备注,;
     */
    private String        remark;
    /**
     * 获得时间,;
     */
    private LocalDateTime obtainTime;

    /**
     * 获得时间,;
     */
    private Integer       obtainTimeDay;

    /**
     * 价值,;
     */
    private BigDecimal    productPrice;

    /**
     * 匹配状态
     */
    private Integer       matchStatus;

    /**
     * 匹配数量
     */
    private Integer       matchCount;
}
