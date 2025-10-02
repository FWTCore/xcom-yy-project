package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 存放地点;location数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Location extends BaseEntityDO {

    /**
     * 系统公司id,;
     */
    private Long   deptId;

    /**
     * 地点名称,;
     */
    private String locationName;

    /**
     * 地点编码,;
     */
    private String locationCode;
}