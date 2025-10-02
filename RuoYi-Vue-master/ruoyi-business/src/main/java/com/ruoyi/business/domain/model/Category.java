package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 分类;category数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntityDO {

    /**
     * 分类名称,;
     */
    private String  categoryName;

    private String  searchName;

    /**
     * 是否填写品牌,;
     */
    private Boolean hasBrand;

    /**
     * 是否填写物资名称,;
     */
    private Boolean hasMaterialName;

    /**
     * 是否填写规格型号,;
     */
    private Boolean hasSpecification;
}