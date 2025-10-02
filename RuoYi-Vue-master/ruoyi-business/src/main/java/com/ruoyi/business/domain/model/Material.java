package com.ruoyi.business.domain.model;

import com.ruoyi.common.core.domain.BaseEntityDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 物资;material数据表的DO对象
 *
 * @author : xcom
 * @date : 2025-9-18
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Material extends BaseEntityDO {

    /**
     * 分类id,;
     */
    private Long   categoryId;

    /**
     * 品牌id,;
     */
    private Long   brandId;

    /**
     * 物资名称,;
     */
    private String materialName;

    /**
     * 规格型号,;
     */
    private String specification;
    private String searchName;
    private String leftSearchMaterialName;
    private String leftSearchSpecification;
}