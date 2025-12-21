package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseMongoDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 原始资产数据mongodb对象
 *
 * @author xcom
 * @date 2025/11/18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OriginalAssetDataDO extends BaseMongoDO {

    /**
     * 原始资产id,;
     */
    @Field("original_asset_id")
    private Long          originalAssetId;

    /**
     * 系统公司id,;
     */
    @Field("dept_id")
    private Long          deptId;

    /**
     * 项目id,;
     */
    @Field("project_id")
    private Long          projectId;
    /**
     * 原始编码,;
     */
    @Field("original_code")
    private String        originalCode;

    /**
     * 分类id,;
     */
    @Field("category_id")
    private Long          categoryId;

    /**
     * 分类名称,;
     */
    @Field("category_name")
    private String        categoryName;

    /**
     * 品牌名称,;
     */
    @Field("brand_name")
    private String        brandName;

    /**
     * 资产名称,;
     */
    @Field("asset_name")
    private String        assetName;

    /**
     * 资产名称统计
     */
    @Field("asset_name_count")
    private Integer       assetNameCount;
    /**
     * 资产名称核对统计
     */
    @Field("asset_name_check_count")
    private Integer       assetNameCheckCount;

    /**
     * 规格型号,;
     */
    @Field("specification")
    private String        specification;

    /**
     * 地点id,;
     */
    @Field("location_id")
    private Long          locationId;

    /**
     * 地点名称,;
     */
    @Field("location_name")
    private String        locationName;
    /**
     * 地点统计
     */
    @Field("location_count")
    private Integer       locationCount;
    /**
     * 地点统计
     */
    @Field("location_check_count")
    private Integer       locationCheckCount;

    /**
     * 管理部门id,;
     */
    @Field("managed_dept_id")
    private Long          managedDeptId;

    /**
     * 管理部门名称,;
     */
    @Field("managed_dept_name")
    private String        managedDeptName;

    /**
     * 使用部门id,;
     */
    @Field("using_dept_id")
    private Long          usingDeptId;

    /**
     * 使用部门名称,;
     */
    @Field("using_dept_name")
    private String        usingDeptName;
    /**
     * 使用部门统计
     */
    @Field("using_dept_count")
    private Integer       usingDeptCount;
    /**
     * 使用部门统计
     */
    @Field("using_dept_check_count")
    private Integer       usingDeptCheckCount;

    /**
     * 管理员工id,;
     */
    @Field("managed_emp_id")
    private Long          managedEmpId;

    /**
     * 管理员工名称,;
     */
    @Field("managed_emp_name")
    private String        managedEmpName;

    /**
     * 使用员工id,;
     */
    @Field("using_emp_id")
    private Long          usingEmpId;

    /**
     * 使用员工名称,;
     */
    @Field("using_emp_name")
    private String        usingEmpName;

    /**
     * 备注,;
     */
    @Field("remark")
    private String        remark;
    /**
     * 获得时间,;
     */
    @Field("obtain_time")
    private LocalDateTime obtainTime;
    /**
     * 获得时间,;
     */
    @Field("obtain_time_day")
    private Integer       obtainTimeDay;
    /**
     * 价值,;
     */
    @Field("product_price")
    private BigDecimal    productPrice;

    /**
     * 关联状态
     */
    @Field("match_status")
    private Integer       matchStatus;

    /**
     * 匹配数量
     */
    @Field("match_count")
    private Integer       matchCount;
}
