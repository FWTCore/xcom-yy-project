package com.ruoyi.business.domain.model.request;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * AssetCheckBO
 *
 * @author xcom
 * @date 2025/11/24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetCheckBO extends AssetCheckCommonBO {

    /**
     * pageNum
     */
    private Integer       pageNum;
    /**
     * pageSize
     */
    private Integer       pageSize;
    /**
     * 所属单位id
     */
    private Long          deptId;
    /**
     * 项目id
     */
    private Long          projectId;
    /**
     * 排序字段
     * 0->门类,1->名称；2->取得时间；3-> 使用部门；4-> 存放地点,5->使用人,6->品牌,7->型号
     */
    private List<Integer> sortFields;

    /**
     * 门类id
     */
    private List<Long>    categoryIds;
    /**
     * 品牌
     */
    private List<String>  brandNames;
    /**
     * 资产名称
     */
    private List<String>  assetNames;
    /**
     * 规格
     */
    private List<String>  specifications;
    /**
     * 获得时间
     */
    private List<Integer> obtainTimeDays;
    /**
     * 存放地点
     */
    private List<Long>    locationIds;
    /**
     * 管理部门
     */
    private List<Long>    managedDeptIds;
    /**
     * 使用部门
     */
    private List<Long>    usingDeptIds;
    /**
     * 管理人
     */
    private List<Long>    managedEmpIds;
    /**
     * 使用人
     */
    private List<Long>    usingEmpIds;

}
