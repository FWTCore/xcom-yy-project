package com.ruoyi.business.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 原始资产对象 original_asset
 *
 * @author xcom
 * @date 2025/10/19
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OriginalAsset extends OriginalAssetDO {

    /**
     * 系统公司名称;
     */
    private String         deptName;
    /**
     * 项目名称,;
     */
    private String       projectName;

    /**
     * 原始编码,;
     */
    private List<String> originalCodes;

    /**
     * 原始编码,;
     */
    private String       searchOriginalCode;

    /**
     * 品牌名称,;
     */
    private String       searchBrandName;

    /**
     * 资产名称,;
     */
    private String       searchAssetName;

    /**
     * 规格型号,;
     */
    private String       searchSpecification;

    /**
     * 地点名称,;
     */
    private String       searchLocationName;

    /**
     * 管理部门名称,;
     */
    private String       searchManagedDeptName;

    /**
     * 使用部门名称,;
     */
    private String       searchUsingDeptName;

    /**
     * 管理员工名称,;
     */
    private String       searchManagedEmpName;

    /**
     * 使用员工名称,;
     */
    private String       searchUsingEmpName;
}
