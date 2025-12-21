package com.ruoyi.business.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.domain.entity.AssetDO;
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
public class Asset extends AssetDO {

    /**
     * 临时编码,;
     */
    private String       leftSearchTemporaryCode;
    /**
     * 临时编码,;
     */
    private String       searchTemporaryCode;

    /**
     * 匹配code
     */
    private String       matchCode;

    /**
     * 原始编码,;
     */
    private List<String> originalCodes;

    /**
     * 原始编码,;
     */
    private String       searchOriginalCode;
    /**
     * 原始子编码
     */
    private String       leftLikeOriginalSubCode;

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
    /**
     * 使用员工名称是否为空,;
     */
    private Boolean      searchUsingEmpNameEmpty;
    /**
     * 采集人名称,;
     */
    private String       searchCollectorUserName;

    /**
     * 搜索 部门id
     */
    private Long         searchDeptId;
    /**
     * 模糊搜索
     */
    private String       searchName;
}