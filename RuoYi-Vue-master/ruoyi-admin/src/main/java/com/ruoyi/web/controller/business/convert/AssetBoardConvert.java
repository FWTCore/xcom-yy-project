package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.web.controller.business.request.AssetBordRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * AssetBoardConvert
 *
 * @author xcom
 * @date 2025/11/23
 */

@Mapper
public interface AssetBoardConvert {
    AssetBoardConvert INSTANCE = Mappers.getMapper(AssetBoardConvert.class);

    /**
     * AssetBordRequest to AssetBordReqBO
     * @param request
     * @return
     */
    AssetBordReqBO toAssetBordReqBO(AssetBordRequest request);
}
