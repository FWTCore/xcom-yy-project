package com.ruoyi.business.domain.model.request;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 资产批量更新请求模型
 *
 * @author xcom
 * @date 2025/10/26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AssetBatchUpdateReqBO extends BaseEntityDO {
    /**
     * id列表
     */
    private List<Long> ids;

    /**
     * 分类id,;
     */
    private Long       categoryId;

    /**
     * 品牌id,;
     */
    private Long       brandId;
    /**
     * 资产名称,;
     */
    private String     assetName;

    /**
     * 规格型号,;
     */
    private String     specification;
    /**
     * 管理部门id,;
     */
    private Long       managedDeptId;

    /**
     * 使用部门id,;
     */
    private Long       usingDeptId;

    /**
     * 管理员工id,;
     */
    private Long       managedEmpId;
    /**
     * 使用员工id,;
     */
    private Long       usingEmpId;
}
