package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.model.Category;
import com.ruoyi.business.mapper.CategoryMapper;
import com.ruoyi.business.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类Service业务层处理
 *
 * @author xcom
 * @date 2025-10-02
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询分类
     *
     * @param id 分类主键
     * @return 分类
     */
    @Override
    public CategoryDO selectCategoryByIds(Long id) {
        return categoryMapper.selectCategoryById(id);
    }

    @Override
    public List<CategoryDO> selectCategoryByIds(List<Long> ids) {
        return categoryMapper.selectCategoryByIds(ids);
    }

    /**
     * 查询分类列表
     *
     * @param category 分类
     * @return 分类
     */
    @Override
    public List<CategoryDO> selectCategoryList(Category category) {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增分类
     *
     * @param category 分类
     * @return 结果
     */
    @Override
    public int insertCategory(CategoryDO category) {
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改分类
     *
     * @param category 分类
     * @return 结果
     */
    @Override
    public int updateCategory(CategoryDO category) {
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Long[] ids) {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除分类信息
     *
     * @param id 分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id) {
        return categoryMapper.deleteCategoryById(id);
    }
}
