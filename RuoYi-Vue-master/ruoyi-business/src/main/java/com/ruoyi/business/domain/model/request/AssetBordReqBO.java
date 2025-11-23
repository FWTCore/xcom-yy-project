package com.ruoyi.business.domain.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 资产报表请求模型
 *
 * @author xcom
 * @date 2025/11/23
 */
@Data
public class AssetBordReqBO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     * 所属单位id
     */
    private Long              deptId;
    /**
     * 项目id
     */
    private Long              projectId;
    /**
     * 指标类型:1->名称；2->部门；3->存放地点
     */
    private Integer           metricsType;
    /**
     * 是否降序，默认降序
     */
    private Boolean           orderFlag;

}
