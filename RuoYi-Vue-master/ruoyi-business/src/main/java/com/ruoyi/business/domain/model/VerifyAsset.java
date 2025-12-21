package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.VerifyAssetDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 核实资产对象 verify_asset
 *
 * @author xcom
 * @date 2025/12/21
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VerifyAsset extends VerifyAssetDO {

    /**
     * 临时编码,;
     */
    private String       searchTemporaryCode;
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
    /**
     * 采集人名称,;
     */
    private String       searchCollectorUserName;
    /**
     * 使用员工名称是否为空,;
     */
    private Boolean      searchUsingEmpNameEmpty;


}
