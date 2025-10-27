package com.ruoyi.business.domain.model.convert;

import com.ruoyi.business.domain.model.response.AssetDetailVO;
import com.ruoyi.business.domain.model.response.OriginalAssetDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * AssetConvert
 *
 * @author xcom
 * @date 2025/10/19
 */

@Mapper
public interface AssetConvert {
    AssetConvert INSTANCE = Mappers.getMapper(AssetConvert.class);

    /**
     * OriginalAssetDetailVO to AssetDetailVO
     * @param detailVO
     * @return
     */
    AssetDetailVO toAssetDetailVO(OriginalAssetDetailVO detailVO);
}
