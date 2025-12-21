package com.ruoyi.business.domain.entity;

import com.ruoyi.common.core.domain.BaseMongoDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * 资产数据mongodb对象
 *
 * @author xcom
 * @date 2025/11/18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AssetDataDO extends BaseMongoDO {

    /**
     * 资产id,;
     */
    @Field("asset_id")
    private Long          assetId;

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
     * 临时编码,;
     */
    @Field("temporary_code")
    private String        temporaryCode;

    /**
     * 原始编码,;
     */
    @Field("original_code")
    private String        originalCode;

    /**
     * 原始子编码
     */
    @Field("original_sub_code")
    private String        originalSubCode;

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
     * 品牌id,;
     */
    @Field("brand_id")
    private Long          brandId;

    /**
     * 品牌名称,;
     */
    @Field("brand_name")
    private String        brandName;

    /**
     * 物资id,;
     */
    @Field("material_id")
    private Long          materialId;

    /**
     * 资产名称,;
     */
    @Field("asset_name")
    private String        assetName;
    /**
     * 资产名称
     */
    @Field("asset_name_count")
    private Integer       assetNameCount;
    /**
     * 资产名称
     */
    @Field("asset_name_check_count")
    private Integer       assetNameCheckCount;

    /**
     * 规格型号,;
     */
    @Field("specification")
    private String        specification;

    /**
     * 生产时间,;
     */
    @Field("production_time")
    private LocalDateTime productionTime;

    /**
     * 资产状态,;
     */
    @Field("asset_status")
    private Integer       assetStatus;

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
     * 地点名称
     */
    @Field("location_count")
    private Integer       locationCount;
    /**
     * 地点名称
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
     * 采集人id,;
     */
    @Field("collector_user_id")
    private Long          collectorUserId;

    /**
     * 采集人名称,;
     */
    @Field("collector_user_name")
    private String        collectorUserName;

    /**
     * 采集时间,;
     */
    @Field("collector_time")
    private LocalDateTime collectorTime;

    /**
     * 备注,;
     */
    @Field("remark")
    private String        remark;

    /**
     * 主图片,;
     */
    @Field("main_image_url")
    private String        mainImageUrl;

    /**
     * 主图片名称,;
     */
    @Field("main_image_name")
    private String        mainImageName;

    /**
     * 图片,;
     */
    @Field("image_url")
    private String        imageUrl;

    /**
     * 图片名称,;
     */
    @Field("image_url_name")
    private String        imageUrlName;

    /**
     * 关联状态
     */
    @Field("match_status")
    private Integer       matchStatus;

    /**
     * 打印状态
     */
    @Field("print_status")
    private Integer       printStatus;
}
