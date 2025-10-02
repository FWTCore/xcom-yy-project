package com.ruoyi.web.controller.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 资产新增、编辑请求模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@ApiModel(value = "资产新增、编辑请求模型")
@Data
public class AssetUpsertRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * 数据id
     */

    @ApiModelProperty(value = "数据id")
    private Long              id;

    /**
     * 临时编码,;
     */
    @ApiModelProperty(value = "临时编码", required = true)
    @NotEmpty(message = "临时编码不能为空")
    private String            temporaryCode;

    /**
     * 原始编码,;
     */
    @ApiModelProperty(value = "原始编码")
    private String            originalCode;

    /**
     * 分类id,;
     */
    @ApiModelProperty(value = "分类id", required = true)
    @NotNull(message = "分类不能为空")
    private Long              categoryId;

    /**
     * 品牌id,;
     */
    @ApiModelProperty(value = "品牌id")
    private Long              brandId;

    /**
     * 品牌名称,;
     */
    @ApiModelProperty(value = "品牌名称")
    private String            brandName;

    /**
     * 物资id,;
     */
    @ApiModelProperty(value = "物资id")
    private Long              materialId;

    /**
     * 资产名称,;
     */
    @ApiModelProperty(value = "资产名称")
    private String            assetName;

    /**
     * 规格型号,;
     */
    @ApiModelProperty(value = "规格型号")
    private String            specification;
    /**
     * 生产时间,;
     */
    @ApiModelProperty(value = "生产时间")
    private LocalDateTime     productionTime;

    /**
     * 地点id,;
     */
    @ApiModelProperty(value = "存放地点id", required = true)
    @NotNull(message = "存放地点不能为空")
    private Long              locationId;

    /**
     * 管理部门id,;
     */
    @ApiModelProperty(value = "管理部门id", required = true)
    @NotNull(message = "管理部门不能为空")
    private Long              managedDeptId;

    /**
     * 使用部门id,;
     */
    @ApiModelProperty(value = "使用部门id", required = true)
    @NotNull(message = "使用部门不能为空")
    private Long              usingDeptId;

    /**
     * 管理员工id,;
     */
    @ApiModelProperty(value = "管理员工id", required = true)
    @NotNull(message = "管理员工不能为空")
    private Long              managedEmpId;

    /**
     * 使用员工id,;
     */
    @ApiModelProperty(value = "使用员工id", required = true)
    @NotNull(message = "使用员工不能为空")
    private Long              usingEmpId;

    /**
     * 备注,;
     */
    @ApiModelProperty(value = "备注")
    private String            remark;

    /**
     * 主图片,;
     */
    @ApiModelProperty(value = "主图片", required = true)
    @NotEmpty(message = "主图片不能为空")
    private String            mainImageUrl;
    /**
     * 主图片名称,;
     */
    @ApiModelProperty(value = "主图片名称")
    @NotEmpty(message = "主图片名称不能为空")
    private String            mainImageName;

    /**
     * 图片,;
     */
    @ApiModelProperty(value = "图片")
    private String            imageUrl;
    /**
     * 图片名称,;
     */
    @ApiModelProperty(value = "图片名称")
    private String            imageUrlName;

}
