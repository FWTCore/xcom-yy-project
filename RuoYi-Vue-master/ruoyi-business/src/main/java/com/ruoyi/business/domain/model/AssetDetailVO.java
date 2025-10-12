package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Resource;

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
    @Excel(name = "所属单位", sort = 1)
    private String deptName;

    /**
     * 项目名称,;
     */
    @Excel(name = "项目", sort = 1)
    private String projectName;
}
