package com.ruoyi.business.convert;

import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Material;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Material Convert
 *
 * @author xcom
 * @date 2025/10/2
 */

@Mapper
public interface MaterialConvert {
    MaterialConvert INSTANCE = Mappers.getMapper(MaterialConvert.class);

    /**
     * MaterialDO to Material
     * @param materialDO
     * @return
     */
    Material toMaterial(MaterialDO materialDO);

}
