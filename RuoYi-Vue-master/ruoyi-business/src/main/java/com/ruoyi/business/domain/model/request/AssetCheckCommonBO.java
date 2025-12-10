package com.ruoyi.business.domain.model.request;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账实核对请求基础模型
 *
 * @author xcom
 * @date 2025/12/9
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AssetCheckCommonBO extends BaseEntityDO {
    /**
     * 门类
     */
    private String searCategory;
    /**
     * 品牌
     */
    private String searBrand;
    /**
     * 名称
     */
    private String searName;
    /**
     * 规格
     */
    private String searSpecif;
    /**
     * 地点
     */
    private String searLocation;
    /**
     * 管理部门
     */
    private String searManagedDept;
    /**
     * 使用部门
     */
    private String searUsingDept;
    /**
     * 管理人
     */
    private String searManagedEmp;
    /**
     * 使用人
     */
    private String searUsingEmp;
}
