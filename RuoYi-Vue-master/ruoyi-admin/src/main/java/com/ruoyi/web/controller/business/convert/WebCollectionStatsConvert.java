package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.model.request.CollectionStatsReqBO;
import com.ruoyi.web.controller.business.request.CollectionStatsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * WebCollectionStatsConvert
 *
 * @author xcom
 * @date 2025/10/26
 */

@Mapper
public interface WebCollectionStatsConvert {
    WebCollectionStatsConvert INSTANCE = Mappers.getMapper(WebCollectionStatsConvert.class);

    /**
     * CollectionStatsRequest to CollectionStatsReqBO
     * @param request
     * @return
     */
    CollectionStatsReqBO toCollectionStatsReqBO(CollectionStatsRequest request);

}
