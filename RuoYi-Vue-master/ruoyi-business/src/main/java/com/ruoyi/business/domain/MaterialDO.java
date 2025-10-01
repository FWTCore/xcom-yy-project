package com.ruoyi.business.domain;

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
public class MaterialDO extends BaseEntityDO {

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

    /**
     * 主图片,;
     */
    private String mainImageUrl;

    /**
     * 主图片名称,;
     */
    private String mainImageName;

    /**
     * 图片,;
     */
    private String imageUrl;

    /**
     * 图片名称,;
     */
    private String imageUrlName;
}