package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.web.controller.business.request.OriginalAssetImportRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * WebOriginalAsset Convert
 *
 * @author xcom
 * @date 2025/10/19
 */

@Mapper
public interface WebOriginalAssetConvert {
    WebOriginalAssetConvert INSTANCE = Mappers.getMapper(WebOriginalAssetConvert.class);

    /**
     * OriginalAssetImportRequest to OriginalAsset
     * @param request
     * @return
     */
    OriginalAsset toOriginalAsset(OriginalAssetImportRequest request);

    /**
     * OriginalAssetImportRequest to OriginalAsset
     * @param request
     * @return
     */
    List<OriginalAsset> toOriginalAsset(List<OriginalAssetImportRequest> request);

}
