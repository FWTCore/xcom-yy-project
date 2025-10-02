package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
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
public class Asset extends BaseEntityDO {

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
    private String        temporaryCode;

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
     * 品牌id,;
     */
    private Long          brandId;

    /**
     * 品牌名称,;
     */
    private String        brandName;

    /**
     * 物资id,;
     */
    private Long          materialId;

    /**
     * 资产名称,;
     */
    private String        assetName;

    /**
     * 规格型号,;
     */
    private String        specification;

    /**
     * 生产时间,;
     */
    private LocalDateTime productionTime;

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
     * 采集人id,;
     */
    private Long          collectorUserId;

    /**
     * 采集人名称,;
     */
    private String        collectorUserName;

    /**
     * 采集时间,;
     */
    private LocalDateTime collectorTime;

    /**
     * 备注,;
     */
    private String        remark;

    /**
     * 主图片,;
     */
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