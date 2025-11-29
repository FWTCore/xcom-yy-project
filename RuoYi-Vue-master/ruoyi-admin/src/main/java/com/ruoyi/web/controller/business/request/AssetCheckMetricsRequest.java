package com.ruoyi.web.controller.business.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.BaseEntityDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * 账实核对指标请求模型
 *
 * @author xcom
 * @date 2025/11/17
 */

@ApiModel(value = "账实核对指标请求模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetCheckMetricsRequest extends BaseEntityDO {


    @ApiModelProperty(value = "pageNum")
    @NotNull(message = "pageNum不能为空")
    private Integer           pageNum;
    @ApiModelProperty(value = "pageSize")
    @NotNull(message = "pageSize不能为空")
    private Integer           pageSize;

    @ApiModelProperty(value = "所属单位id")
    @NotNull(message = "所属单位不能为空")
    private Long              deptId;
    @ApiModelProperty(value = "项目id")
    @NotNull(message = "所属项目不能为空")
    private Long              projectId;
    @ApiModelProperty(value = "指标类型:0->门类,1->品牌；2->名称；3-> 型号；4-> 取得时间,5->存放地点,6->管理部门,7->使用部门,8->管理人,9->使用人")
    @NotNull(message = "指标类型不能为空")
    private Integer           metricsType;
    @ApiModelProperty(value = "模糊搜索")
    private String            searchName;

}
