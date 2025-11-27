package com.ruoyi.web.controller.business.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 账实核对请求模型
 *
 * @author xcom
 * @date 2025/11/17
 */

@ApiModel(value = "账实核对请求模型")
@Data
public class AssetCheckRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

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
    /**
     * 排序字段
     * 0->门类,1->名称；2->取得时间；3-> 使用部门；4-> 存放地点,5->使用人,6->品牌,7->型号
     */
    @ApiModelProperty(value = "排序字段")
    private List<Integer>     sortFields;

    /**
     * 门类id
     */
    @ApiModelProperty(value = "门类id")
    private List<Long>        categoryIds;
    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private List<String>      brandNames;
    /**
     * 资产名称
     */
    @ApiModelProperty(value = "资产名称")
    private List<String>      assetNames;
    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private List<String>      specifications;
    /**
     * 获得时间
     */
    @ApiModelProperty(value = "获得时间")
    private List<Integer>     obtainTimeDays;
    /**
     * 存放地点
     */
    @ApiModelProperty(value = "存放地点")
    private List<Long>        locationIds;
    /**
     * 管理部门
     */
    @ApiModelProperty(value = "管理部门")
    private List<Long>        managedDeptIds;
    /**
     * 使用部门
     */
    @ApiModelProperty(value = "使用部门")
    private List<Long>        usingDeptIds;
    /**
     * 管理人
     */
    @ApiModelProperty(value = "管理人")
    private List<Long>        managedEmpIds;
    /**
     * 使用人
     */
    @ApiModelProperty(value = "使用人")
    private List<Long>        usingEmpIds;

}