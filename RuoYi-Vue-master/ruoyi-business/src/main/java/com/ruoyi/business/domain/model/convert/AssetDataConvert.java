package com.ruoyi.business.domain.model.convert;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.entity.AssetDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * AssetDataConvert
 *
 * @author xcom
 * @date 2025/11/23
 */

@Mapper
public interface AssetDataConvert {
    AssetDataConvert INSTANCE = Mappers.getMapper(AssetDataConvert.class);

    /**
     * AssetDO to AssetDataDO
     * @param assetDO
     * @return
     */
    @Mappings({ @Mapping(target = "id", ignore = true), @Mapping(target = "assetId", source = "id"), })
    AssetDataDO toAssetDataDO(AssetDO assetDO);

}
