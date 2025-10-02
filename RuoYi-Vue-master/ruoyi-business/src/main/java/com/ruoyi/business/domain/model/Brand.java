package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 品牌;brand数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseEntityDO {

    /**
     * 分类id,;
     */
    private Long   categoryId;

    /**
     * 品牌名称,;
     */
    private String brandName;
    private String searchName;
}