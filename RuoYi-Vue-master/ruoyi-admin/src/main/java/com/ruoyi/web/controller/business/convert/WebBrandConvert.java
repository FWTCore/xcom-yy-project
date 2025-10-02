package com.ruoyi.web.controller.business.convert;

import com.ruoyi.business.domain.entity.BrandDO;
import com.ruoyi.business.domain.model.BrandDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Brand Convert
 *
 * @author xcom
 * @date 2025/10/2
 */

@Mapper
public interface WebBrandConvert {
    WebBrandConvert INSTANCE = Mappers.getMapper(WebBrandConvert.class);

    /**
     *  BrandDO to BrandDetailVO
     * @param data
     * @return
     */
    BrandDetailVO toBrandDetailVO(BrandDO data);
    /**
     *  BrandDO to BrandDetailVO
     * @param data
     * @return
     */
    List<BrandDetailVO> toBrandDetailVO(List<BrandDO> data);
}
