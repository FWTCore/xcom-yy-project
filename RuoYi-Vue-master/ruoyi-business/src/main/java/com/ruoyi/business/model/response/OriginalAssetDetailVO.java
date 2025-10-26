package com.ruoyi.business.model.response;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * OriginalAssetDetailVO
 *
 * @author xcom
 * @date 2025/10/19
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OriginalAssetDetailVO extends OriginalAssetDO {

    /**
     * 部门名称,;
     */
    @Excel(name = "所属单位", sort = 1)
    private String deptName;

    /**
     * 项目名称,;
     */
    @Excel(name = "项目", sort = 2)
    private String projectName;
}
