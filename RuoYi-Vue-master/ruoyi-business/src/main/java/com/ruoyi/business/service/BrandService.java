package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.BrandDO;
import com.ruoyi.business.domain.model.Brand;
import com.ruoyi.business.domain.model.BrandDetailVO;

import java.util.List;

/**
 * 品牌Service接口
 *
 * @author xcom
 * @date 2025-10-02
 */
public interface BrandService {
    /**
     * 查询品牌
     *
     * @param id 品牌主键
     * @return 品牌
     */
    BrandDO selectBrandById(Long id);

    /**
     * 查询品牌列表
     *
     * @param brand 品牌
     * @return 品牌集合
     */
    List<BrandDO> selectBrandList(Brand brand);
    /**
     * 查询品牌列表
     *
     * @param brand 品牌
     * @return 品牌集合
     */
    List<BrandDetailVO> selectBrandDetailList(Brand brand);

    /**
     * 新增品牌
     *
     * @param brand 品牌
     * @return 结果
     */
    int insertBrand(BrandDO brand);

    /**
     * 修改品牌
     *
     * @param brand 品牌
     * @return 结果
     */
    int updateBrand(BrandDO brand);

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的品牌主键集合
     * @return 结果
     */
    int deleteBrandByIds(Long[] ids);

    /**
     * 删除品牌信息
     *
     * @param id 品牌主键
     * @return 结果
     */
    int deleteBrandById(Long id);
}
