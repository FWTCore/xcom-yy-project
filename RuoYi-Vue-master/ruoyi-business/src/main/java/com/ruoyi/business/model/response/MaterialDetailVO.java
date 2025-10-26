package com.ruoyi.business.model.response;

import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 物资返回模型
 *
 * @author xcom
 * @date 2025/10/2
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MaterialDetailVO extends MaterialDO {

    /**
     * 分类名称,;
     */
    private String  categoryName;

    /**
     * 品牌名称,;
     */
    private String  brandName;

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
