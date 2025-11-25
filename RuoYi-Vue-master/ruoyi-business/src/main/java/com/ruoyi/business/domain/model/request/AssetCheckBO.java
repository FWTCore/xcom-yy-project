package com.ruoyi.business.domain.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * AssetCheckBO
 *
 * @author xcom
 * @date 2025/11/24
 */
@Data
public class AssetCheckBO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     * pageNum
     */
    private Integer           pageNum;
    /**
     * pageSize
     */
    private Integer           pageSize;
    /**
     * 所属单位id
     */
    private Long              deptId;
    /**
     * 项目id
     */
    private Long              projectId;
    /**
     * 排序字段
     */
    private List<String>      sortFields;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;
}
