package com.ruoyi.business.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

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
     * 临时编码,;
     */
    private String        leftSearchTemporaryCode;
    /**
     * 临时编码,;
     */
    private String        searchTemporaryCode;

    /**
     * 匹配code
     */
    private String        matchCode;

    /**
     * 原始编码,;
     */
    private String        originalCode;
    /**
     * 原始编码,;
     */
    private List<String>  originalCodes;

    /**
     * 原始编码,;
     */
    private String        searchOriginalCode;
    /**
     * 原始子编码
     */
    private String        originalSubCode;
    /**
     * 原始子编码
     */
    private String        leftLikeOriginalSubCode;

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
     * 资产名称,;
     */
    private String        searchAssetName;

    /**
     * 规格型号,;
     */
    private String        specification;

    /**
     * 规格型号,;
     */
    private String        searchSpecification;

    /**
     * 生产时间,;
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime productionTime;

    /**
     * 资产状态,;
     */
    private Integer       assetStatus;

    /**
     * 地点id,;
     */
    private Long          locationId;

    /**
     * 地点名称,;
     */
    private String        locationName;

    /**
     * 地点名称,;
     */
    private String        searchLocationName;

    /**
     * 管理部门id,;
     */
    private Long          managedDeptId;

    /**
     * 管理部门名称,;
     */
    private String        managedDeptName;
    /**
     * 管理部门名称,;
     */
    private String        searchManagedDeptName;

    /**
     * 使用部门id,;
     */
    private Long          usingDeptId;

    /**
     * 使用部门名称,;
     */
    private String        usingDeptName;
    /**
     * 使用部门名称,;
     */
    private String        searchUsingDeptName;

    /**
     * 管理员工id,;
     */
    private Long          managedEmpId;

    /**
     * 管理员工名称,;
     */
    private String        managedEmpName;
    /**
     * 管理员工名称,;
     */
    private String        searchManagedEmpName;

    /**
     * 使用员工id,;
     */
    private Long          usingEmpId;

    /**
     * 使用员工名称,;
     */
    private String        usingEmpName;
    /**
     * 使用员工名称,;
     */
    private String        searchUsingEmpName;

    /**
     * 采集人id,;
     */
    private Long          collectorUserId;

    /**
     * 采集人名称,;
     */
    private String        collectorUserName;
    /**
     * 采集人名称,;
     */
    private String        searchCollectorUserName;

    /**
     * 采集时间,;
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
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

    /**
     * 搜索 部门id
     */
    private Long          searchDeptId;
    /**
     * 模糊搜索
     */
    private String        searchName;
    /**
     * 打印状态
     */
    private Integer       printStatus;
    /**
     * 打印状态
     */
    private Integer       matchStatus;
}