package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.AssetDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 资产 明细
 *
 * @author xcom
 * @date 2025/10/7
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AssetDetailVO extends AssetDO {

    /**
     * 部门名称,;
     */
    private String deptName;

    /**
     * 项目名称,;
     */
    private String projectName;
}
