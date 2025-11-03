package com.ruoyi.business.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntityDO;
import com.ruoyi.common.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 资产;asset数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AssetDO extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long          deptId;

    /**
     * 项目id,;
     */
    private Long          projectId;

    /**
     * 临时编码,;
     */
    @Excel(name = "临时编码")
    private String        temporaryCode;

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
     * 品牌id,;
     */
    private Long          brandId;

    /**
     * 品牌名称,;
     */
    @Excel(name = "品牌名称")
    private String        brandName;

    /**
     * 物资id,;
     */
    private Long          materialId;

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
     * 生产时间,;
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    @Excel(name = "生产时间", dateFormat = "yyyy-MM-dd")
    private LocalDateTime productionTime;

    private String        productionTimeStr;

    public String getProductionTimeStr() {
        return DateUtils.convertDateString(this.productionTime);
    }

    /**
     * 资产状态,;
     */
    @Excel(name = "资产状态", readConverterExp = "0=闲置,1=在用,2=毁损")
    private Integer       assetStatus;

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
     * 采集人id,;
     */
    private Long          collectorUserId;

    /**
     * 采集人名称,;
     */
    @Excel(name = "采集人")
    private String        collectorUserName;

    /**
     * 采集时间,;
     */
    @Excel(name = "采集时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime collectorTime;

    /**
     * 备注,;
     */
    @Excel(name = "备注")
    private String        remark;

    /**
     * 主图片,;
     */
    @Excel(name = "主图片", cellType = Excel.ColumnType.IMAGE, height = 100, width = 20)
    private String        mainImageUrl;

    /**
     * 主图片名称,;
     */
    private String        mainImageName;

    /**
     * 图片,;
     */
    private String        imageUrl;

    /**
     * 图片名称,;
     */
    private String        imageUrlName;
}