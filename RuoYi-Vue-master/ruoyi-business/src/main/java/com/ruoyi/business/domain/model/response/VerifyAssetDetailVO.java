package com.ruoyi.business.domain.model.response;

import com.ruoyi.business.domain.entity.VerifyAssetDO;
import com.ruoyi.common.annotation.Excel;
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
public class VerifyAssetDetailVO extends VerifyAssetDO {

    /**
     * 系统公司名称;
     */
    @Excel(name = "所属单位", sort = 1)
    private String deptName;
    /**
     * 项目名称,;
     */
    @Excel(name = "项目", sort = 2)
    private String projectName;
}
