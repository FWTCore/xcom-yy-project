package com.ruoyi.business.domain.model.convert;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.entity.OriginalAssetDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * OriginalAssetDataConvert
 *
 * @author xcom
 * @date 2025/11/23
 */

@Mapper
public interface OriginalAssetDataConvert {
    OriginalAssetDataConvert INSTANCE = Mappers.getMapper(OriginalAssetDataConvert.class);

    /**
     * OriginalAssetDO to OriginalAssetDataDO
     * @param originalAssetDO
     * @return
     */
    @Mappings({ @Mapping(target = "id", ignore = true), @Mapping(target = "originalAssetId", source = "id"), })
    OriginalAssetDataDO toOriginalAssetDataDO(OriginalAssetDO originalAssetDO);
}
