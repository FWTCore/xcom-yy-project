package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.business.domain.entity.BrandDO;
import com.ruoyi.business.domain.model.Brand;
import com.ruoyi.business.domain.model.BrandDetailVO;
import com.ruoyi.business.mapper.BrandMapper;
import com.ruoyi.business.service.BrandService;
import com.ruoyi.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 品牌Service业务层处理
 *
 * @author xcom
 * @date 2025-10-02
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private BrandMapper brandMapper;

    /**
     * 查询品牌
     *
     * @param id 品牌主键
     * @return 品牌
     */
    @Override
    public BrandDO selectBrandById(Long id) {
        return brandMapper.selectBrandById(id);
    }

    /**
     * 查询品牌列表
     *
     * @param brand 品牌
     * @return 品牌
     */
    @Override
    public List<BrandDO> selectBrandList(Brand brand) {
        return brandMapper.selectBrandList(brand);
    }

    @Override
    public List<BrandDetailVO> selectBrandDetailList(Brand brand) {
        return brandMapper.selectBrandDetailList(brand);
    }

    /**
     * 新增品牌
     *
     * @param brand 品牌
     * @return 结果
     */
    @Override
    public int insertBrand(BrandDO brand) {
        brand.setBaseFieldValue();
        return brandMapper.insertBrand(brand);
    }

    /**
     * 修改品牌
     *
     * @param brand 品牌
     * @return 结果
     */
    @Override
    public int updateBrand(BrandDO brand) {
        brand.setUpdatedFieldValue();
        return brandMapper.updateBrand(brand);
    }

    /**
     * 批量删除品牌
     *
     * @param ids 需要删除的品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandByIds(Long[] ids) {
        return brandMapper.deleteBrandByIds(ids);
    }

    /**
     * 删除品牌信息
     *
     * @param id 品牌主键
     * @return 结果
     */
    @Override
    public int deleteBrandById(Long id) {
        return brandMapper.deleteBrandById(id);
    }

    @Override
    public Long insertNotExistBrand(Long categoryId, String brandName) {
        Brand brandRequest = new Brand();
        brandRequest.setCategoryId(categoryId);
        brandRequest.setBrandName(brandName);
        List<BrandDO> brandList = this.selectBrandList(brandRequest);
        if (CollectionUtils.isEmpty(brandList)) {
            // 创建品牌
            BrandDO newBrand = new BrandDO();
            newBrand.setCategoryId(categoryId);
            newBrand.setBrandName(brandName);
            this.insertBrand(newBrand);
            return newBrand.getId();
        } else {
            if (brandList.size() > 1) {
                throw new ServiceException("品牌存在多个，数据异常");
            }
            BrandDO existBrand = brandList.get(0);
            return existBrand.getId();
        }
    }
}