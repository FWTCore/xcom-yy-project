package com.ruoyi.business.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
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
public class OriginalAssetDO extends BaseEntityDO {

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
    @Excel(name = "原始编码")
    private String        originalCode;

    /**
     * 分类id,;
     */
    private Long          categoryId;

    /**
     * 分类名称,;
     */
    @Excel(name = "门类名称")
    private String        categoryName;

    /**
     * 品牌名称,;
     */
    @Excel(name = "品牌")
    private String        brandName;

    /**
     * 资产名称,;
     */
    @Excel(name = "资产名称")
    private String        assetName;

    /**
     * 规格型号,;
     */
    @Excel(name = "规格型号")
    private String        specification;

    /**
     * 地点id,;
     */
    private Long          locationId;

    /**
     * 地点名称,;
     */
    @Excel(name = "地点名称")
    private String        locationName;

    /**
     * 管理部门id,;
     */
    private Long          managedDeptId;

    /**
     * 管理部门名称,;
     */
    @Excel(name = "管理部门")
    private String        managedDeptName;

    /**
     * 使用部门id,;
     */
    private Long          usingDeptId;

    /**
     * 使用部门名称,;
     */
    @Excel(name = "使用部门")
    private String        usingDeptName;

    /**
     * 管理员工id,;
     */
    private Long          managedEmpId;

    /**
     * 管理员工名称,;
     */
    @Excel(name = "管理员工")
    private String        managedEmpName;

    /**
     * 使用员工id,;
     */
    private Long          usingEmpId;

    /**
     * 使用员工名称,;
     */
    @Excel(name = "使用员工")
    private String        usingEmpName;

    /**
     * 备注,;
     */
    @Excel(name = "备注")
    private String        remark;
    /**
     * 获得时间,;
     */
    @Excel(name = "获得时间")
    @NotNull(message = "获得时间不能为空")
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime obtainTime;

    /**
     * 获得时间,;
     */
    private Integer       obtainTimeDay;

    /**
     * 价值,;
     */
    @Excel(name = "价值")
    @NotNull(message = "价值不能为空")
    private BigDecimal    productPrice;

    /**
     * 关联状态
     */
    @Excel(name = "关联状态", readConverterExp = "0=未关联,1=已关联,2=盘盈,3=盘实,4=待核实")
    private Integer       matchStatus;

    /**
     * 匹配数量
     */
    @Excel(name = "匹配数量")
    private Integer       matchCount;

}
