package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.business.domain.entity.BrandDO;
import com.ruoyi.business.domain.model.Brand;
import com.ruoyi.business.domain.model.BrandDetailVO;
import com.ruoyi.business.mapper.BrandMapper;
import com.ruoyi.business.service.BrandService;
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
}