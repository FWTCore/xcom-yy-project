package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.MaterialDO;
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
public class Material extends MaterialDO {

    /**
     * 分类id,;
     */
    private Long   categoryId;

    /**
     * 品牌id,;
     */
    private Long   brandId;
    /**
     * 品牌id,;
     */
    private String   brandName;

    /**
     * 物资名称,;
     */
    private String materialName;
    private String searchMaterialName;

    /**
     * 规格型号,;
     */
    private String specification;
    private String searchSpecification;

    private String searchName;
    private String leftSearchMaterialName;
    private String leftSearchSpecification;
}