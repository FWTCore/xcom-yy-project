package com.ruoyi.business.domain.model;

import com.ruoyi.business.domain.entity.BrandDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 品牌返回模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BrandDetailVO extends BrandDO {

    private static final long serialVersionUID = -7059884802225152123L;

    /**
     * 分类名称,;
     */
    private String            categoryName;

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
