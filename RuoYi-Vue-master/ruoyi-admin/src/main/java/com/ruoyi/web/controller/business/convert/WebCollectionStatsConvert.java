package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.model.request.CollectionStatsReqBO;
import com.ruoyi.web.controller.business.request.CollectionStatsRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
