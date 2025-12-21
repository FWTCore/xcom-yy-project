package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.BrandDO;
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
public class Brand extends BrandDO {

    /**
     * 品牌名称,;
     */
    private String searchName;
}