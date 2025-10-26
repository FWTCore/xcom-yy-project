package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.web.controller.business.request.OriginalAssetImportRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Mappings({ @Mapping(target = "obtainTime", source = "obtainTime", qualifiedByName = "toLocalTime"),
                @Mapping(target = "productPrice", source = "productPrice", qualifiedByName = "toBigDecimal"), })
    OriginalAsset toOriginalAsset(OriginalAssetImportRequest request);

    /**
     * OriginalAssetImportRequest to OriginalAsset
     * @param request
     * @return
     */
    List<OriginalAsset> toOriginalAsset(List<OriginalAssetImportRequest> request);

    /**
     * toLocalTime
     * @param text
     * @return
     */
    @Named("toLocalTime")
    default LocalDateTime toLocalTime(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        // 已经是完整格式
        if (StringUtils.isNotEmpty(text) && text.length() > 10) {
            return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        // 只有日期，补时间
        return LocalDate.parse(text).atStartOfDay();
    }

    /**
     * toBigDecimal
     * @param text
     * @return
     */
    @Named("toBigDecimal")
    default BigDecimal toBigDecimal(String text) {
        if (StringUtils.isNotEmpty(text)) {
            try {
                return new BigDecimal(text.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("无效数值格式: " + text, e);
            }
        }
        return null;
    }

}
