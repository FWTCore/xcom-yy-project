package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.web.controller.business.request.AssetUpsertRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * WebAsset Convert
 *
 * @author xcom
 * @date 2025/10/2
 */

@Mapper
public interface WebAssetConvert {
    WebAssetConvert INSTANCE = Mappers.getMapper(WebAssetConvert.class);

    /**
     * AssetUpsertRequest to AssetDO
     * @param request
     * @return
     */
    AssetDO toAssetDO(AssetUpsertRequest request);

    /**
     * Asset to AssetDO
     * @param asset
     * @return
     */
    AssetDO toAssetDO(Asset asset);
}
