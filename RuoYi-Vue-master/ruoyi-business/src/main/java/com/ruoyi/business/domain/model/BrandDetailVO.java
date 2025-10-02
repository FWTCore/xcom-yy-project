package com.ruoyi.business.domain.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌返回模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@Data
public class BrandDetailVO implements Serializable {

    private static final long serialVersionUID = -7059884802225152123L;
    private Long              id;

    /**
     * 分类id,;
     */
    private Long              categoryId;
    /**
     * 分类名称,;
     */
    private String            categoryName;

    /**
     * 品牌名称,;
     */
    private String            brandName;

    /**
     * 是否填写品牌,;
     */
    private Boolean           hasBrand;

    /**
     * 是否填写物资名称,;
     */
    private Boolean           hasMaterialName;

    /**
     * 是否填写规格型号,;
     */
    private Boolean           hasSpecification;
}
