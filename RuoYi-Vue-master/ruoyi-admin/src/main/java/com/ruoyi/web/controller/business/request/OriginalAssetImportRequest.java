package com.ruoyi.web.controller.business.request;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 原始资产导入请求模型
 *
 * @author xcom
 * @date 2025/10/19
 */

@ApiModel(value = "原始资产导入请求模型")
@Data
public class OriginalAssetImportRequest implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    /**
     * 部门名称,;
     */
    @Excel(name = "所属单位", sort = 1, cellType = Excel.ColumnType.TEXT, prompt = "所属单位")
    private String deptName;

    /**
     * 项目名称,;
     */
    @Excel(name = "项目", sort = 2, cellType = Excel.ColumnType.TEXT, prompt = "项目")
    private String projectName;
    /**
     * 原始编码,;
     */
    @Excel(name = "原始编码", cellType = Excel.ColumnType.TEXT, prompt = "原始编码")
    private String originalCode;
    /**
     * 分类名称,;
     */
    @Excel(name = "门类名称", cellType = Excel.ColumnType.TEXT, prompt = "门类名称")
    private String categoryName;
    /**
     * 品牌名称,;
     */
    @Excel(name = "品牌", cellType = Excel.ColumnType.TEXT, prompt = "品牌")
    private String brandName;
    /**
     * 资产名称,;
     */
    @Excel(name = "资产名称", cellType = Excel.ColumnType.TEXT, prompt = "资产名称")
    private String assetName;
    /**
     * 规格型号,;
     */
    @Excel(name = "规格型号", cellType = Excel.ColumnType.TEXT, prompt = "规格型号")
    private String specification;
    /**
     * 地点名称,;
     */
    @Excel(name = "地点名称", cellType = Excel.ColumnType.TEXT, prompt = "地点名称")
    private String locationName;
    /**
     * 管理部门名称,;
     */
    @Excel(name = "管理部门", cellType = Excel.ColumnType.TEXT, prompt = "管理部门")
    private String managedDeptName;
    /**
     * 使用部门名称,;
     */
    @Excel(name = "使用部门", cellType = Excel.ColumnType.TEXT, prompt = "使用部门")
    private String usingDeptName;
    /**
     * 管理员工名称,;
     */
    @Excel(name = "管理员工", cellType = Excel.ColumnType.TEXT, prompt = "管理员工")
    private String managedEmpName;
    /**
     * 使用员工名称,;
     */
    @Excel(name = "使用员工", cellType = Excel.ColumnType.TEXT, prompt = "使用员工")
    private String usingEmpName;
    /**
     * 备注,;
     */
    @Excel(name = "备注", cellType = Excel.ColumnType.TEXT, prompt = "备注")
    private String remark;



}
