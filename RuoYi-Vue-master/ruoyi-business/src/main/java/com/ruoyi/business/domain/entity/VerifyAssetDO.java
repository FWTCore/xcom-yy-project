package com.ruoyi.business.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 核实资产对象 verify_asset
 *
 * @author xcom
 * @date 2025/12/21
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VerifyAssetDO extends BaseEntityDO {

    /**
     * 系统公司id
     */
    private Long          deptId;

    /**
     * 项目id
     */
    private Long          projectId;

    /**
     * 临时编码
     */
    @Excel(name = "临时编码")
    private String        temporaryCode;

    /**
     * 原始编码
     */
    @Excel(name = "原始编码")
    private String        originalCode;

    /**
     * 原始子编码
     */
    @Excel(name = "原始子编码")
    private String        originalSubCode;

    /**
     * 分类id
     */
    private Long          categoryId;

    /**
     * 分类名称
     */
    @Excel(name = "分类名称")
    private String        categoryName;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称")
    private String        brandName;

    /**
     * 资产名称
     */
    @Excel(name = "资产名称")
    private String        assetName;

    /**
     * 规格型号
     */
    @Excel(name = "规格型号")
    private String        specification;

    /**
     * 生产时间
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    @Excel(name = "生产时间", dateFormat = "yyyy-MM-dd")
    private LocalDateTime productionTime;

    /**
     * 地点id
     */
    private Long          locationId;

    /**
     * 地点名称
     */
    @Excel(name = "地点名称")
    private String        locationName;

    /**
     * 管理部门id
     */
    private Long          managedDeptId;

    /**
     * 管理部门名称
     */
    @Excel(name = "管理部门名称")
    private String        managedDeptName;

    /**
     * 使用部门id
     */
    private Long          usingDeptId;

    /**
     * 使用部门名称
     */
    @Excel(name = "使用部门名称")
    private String        usingDeptName;

    /**
     * 管理员工id
     */
    private Long          managedEmpId;

    /**
     * 管理员工名称
     */
    @Excel(name = "管理员工名称")
    private String        managedEmpName;

    /**
     * 使用员工id
     */
    private Long          usingEmpId;

    /**
     * 使用员工名称
     */
    @Excel(name = "使用员工名称")
    private String        usingEmpName;

    /**
     * 采集人id
     */
    @Excel(name = "采集人id")
    private Long          collectorUserId;

    /** 采集人名称 */
    private String        collectorUserName;

    /**
     * 采集时间
     */
    @Excel(name = "采集时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime collectorTime;

    /**
     * 主图片
     */
    private String        mainImageUrl;

    /**
     * 主图片名称
     */
    private String        mainImageName;

    /**
     * 图片
     */
    private String        imageUrl;

    /**
     * 图片名称
     */
    private String        imageUrlName;

    /**
     * 资产状态
     */
    @Excel(name = "资产状态", readConverterExp = "0=闲置,1=在用,2=毁损")
    private Long          assetStatus;

    /**
     * 打印状态
     */
    @Excel(name = "打印状态", readConverterExp = "0=无需打印,1=未打印,2=已打印")
    private Long          printStatus;

    /**
     * 获得时间
     */
    @Excel(name = "获得时间")
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime obtainTime;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private BigDecimal    productPrice;

    /**
     * 获得时间
     */
    @Excel(name = "获得时间")
    private Integer       obtainTimeDay;

    /**
     * 数量
     */
    private Long          quantity;

    /**
     * 累计折旧
     */
    private BigDecimal    accumulatedDepreciation;

    /**
     * 净值
     */
    private BigDecimal    carryingValue;

    /**
     * 关联状态
     */
    @Excel(name = "关联状态", readConverterExp = "0=未关联,1=已关联,2=盘盈,3=盘实,4=待核实")
    private Integer       relationalStatus;
    /**
     * 备注,;
     */
    private String        remark;

}
