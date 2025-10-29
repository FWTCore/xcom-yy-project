package com.ruoyi.web.controller.business.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 资产批量更新请求模型
 *
 * @author xcom
 * @date 2025/10/26
 */

@ApiModel(value = "资产批量更新请求模型")
@Data
public class AssetBatchUpdateRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * id列表
     */
    @NotNull(message = "批量编辑数据不能为空")
    private List<Long>        ids;

    /**
     * 分类id,;
     */
    private Long              categoryId;

    /**
     * 品牌id,;
     */
    private Long              brandId;
    /**
     * 资产名称,;
     */
    private String            assetName;

    /**
     * 规格型号,;
     */
    private String            specification;
    /**
     * 生产时间,;
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime     productionTime;
    /**
     * 资产状态,;
     */
    private Integer           assetStatus;
    /**
     * 地点id,;
     */
    private Long              locationId;
    /**
     * 管理部门id,;
     */
    private Long              managedDeptId;

    /**
     * 使用部门id,;
     */
    private Long              usingDeptId;

    /**
     * 管理员工id,;
     */
    private Long              managedEmpId;
    /**
     * 使用员工id,;
     */
    private Long              usingEmpId;
}
