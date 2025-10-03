package com.ruoyi.common.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.utils.TableBaseFieldUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 实体基础模型 DO
 *
 * @author xcom
 * @date 2025/10/2
 */

@Data
public class BaseEntityDO implements Serializable {

    private static final long   serialVersionUID = -7059884802225152123L;

    /**
     * 自动增长主键
     */
    private Long                id;

    /**
     * 逻辑删除
     * <p>
     */
    private Boolean             deleteFlag;

    // === 创建信息

    /**
     * 创建者ID
     */
    private Long                createdById;
    /**
     * 创建者名称
     */
    private String              createdByName;
    /**
     * 创建时间
     */
    private LocalDateTime       createdTime;

    // === 更新信息

    /**
     * 最后更新者ID
     */
    private Long                updatedById;

    /**
     * 最后更新者名称
     */
    private String              updatedByName;
    /**
     * 最后更新时间
     */
    private LocalDateTime       updatedTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;

    /**
     * 设置全部基础字段
     */
    public void setBaseFieldValue() {
        TableBaseFieldUtil.setBaseFieldValue(this);
    }

    /**
     * 设置创建基础字段
     */
    public void setCreatedFieldValue() {
        TableBaseFieldUtil.setCreatedFieldValue(this);
    }

    /**
     * 设置更新基础字段
     */
    public void setUpdatedFieldValue() {
        TableBaseFieldUtil.setUpdatedFieldValue(this);
    }

}
