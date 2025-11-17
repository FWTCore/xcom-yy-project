package com.ruoyi.common.core.domain;

import com.ruoyi.common.utils.TableBaseFieldUtil;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基础模型 DO
 *
 * @author xcom
 * @date 2025/11/17
 */
@Data
public class BaseMongoDO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;

    @MongoId
    private String            id;

    /**
     * 逻辑删除
     * <p>
     */
    @Field("delete_flag")
    private Boolean           deleteFlag;

    // === 创建信息

    /**
     * 创建者ID
     */
    @Field("created_by_id")
    private Long              createdById;
    /**
     * 创建者名称
     */
    @Field("created_by_name")
    private String            createdByName;
    /**
     * 创建时间
     */
    @Field("created_time")
    private LocalDateTime     createdTime;

    // === 更新信息

    /**
     * 最后更新者ID
     */
    @Field("updated_by_id")
    private Long              updatedById;

    /**
     * 最后更新者名称
     */
    @Field("updated_by_name")
    private String            updatedByName;
    /**
     * 最后更新时间
     */
    @Field("updated_time")
    private LocalDateTime     updatedTime;

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
