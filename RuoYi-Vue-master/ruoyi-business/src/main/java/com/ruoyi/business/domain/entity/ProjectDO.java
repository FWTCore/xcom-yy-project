package com.ruoyi.business.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ruoyi.business.utils.LenientLocalDateTimeDeserializer;
import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;

/**
 * 项目;project数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProjectDO extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long          deptId;

    /**
     * 项目名称,;
     */
    private String        projectName;

    /**
     * 项目开始周期,;
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime startDate;

    /**
     * 项目结束周期,;
     */
    @JsonDeserialize(using = LenientLocalDateTimeDeserializer.class)
    private LocalDateTime endDate;

    /**
     * 项目状态,;
     */
    private Integer       projectStatus;
    /**
     * 项目状态,;
     */
    private String        projectStatusDesc;

    /**
     * 备注,;
     */
    private String        remark;

    public String getProjectStatusDesc() {
        if (ObjectUtils.isEmpty(this.projectStatus)) {
            return "未开始";
        }
        if (this.projectStatus.equals(1)) {

            return "进行中";
        }
        if (this.projectStatus.equals(2)) {
            return "已结束";
        }
        return "未开始";
    }

}