package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.model.request.AssetCheckBO;
import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.request.AssetCheckRelationalReqBO;
import com.ruoyi.web.controller.business.request.AssetCheckMetricsRequest;
import com.ruoyi.web.controller.business.request.AssetCheckRelationalRequest;
import com.ruoyi.web.controller.business.request.AssetCheckRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * AssetCheckConvert
 *
 * @author xcom
 * @date 2025/11/23
 */

@Mapper
public interface AssetCheckConvert {
    AssetCheckConvert INSTANCE = Mappers.getMapper(AssetCheckConvert.class);

    /**
     * AssetCheckMetricsRequest to AssetCheckMetricsReqBO
     * @param request
     * @return
     */
    AssetCheckMetricsReqBO toAssetCheckMetricsReqBO(AssetCheckMetricsRequest request);

    /**
     * AssetCheckRequest to AssetCheckBO
     * @param request
     * @return
     */
    AssetCheckBO toAssetCheckBO(AssetCheckRequest request);

    /**
     * AssetCheckRelationalRequest to AssetCheckRelationalReqBO
     * @param request
     * @return
     */
    AssetCheckRelationalReqBO toAssetCheckRelationalReqBO(AssetCheckRelationalRequest request);

}
