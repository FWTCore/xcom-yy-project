package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.CategoryDO;
import com.ruoyi.business.domain.model.Category;

import java.util.List;

/**
 * 分类Service接口
 *
 * @author xcom
 * @date 2025-10-02
 */

public interface CategoryService {
    /**
     * 查询分类
     *
     * @param id 分类主键
     * @return 分类
     */
    CategoryDO selectCategoryByIds(Long id);

    /**
     * 查询分类
     *
     * @param ids 分类主键
     * @return 分类
     */
    List<CategoryDO> selectCategoryByIds(List<Long> ids);

    /**
     * 查询分类列表
     *
     * @param category 分类
     * @return 分类集合
     */
    List<CategoryDO> selectCategoryList(Category category);

    /**
     * 新增分类
     *
     * @param category 分类
     * @return 结果
     */
    int insertCategory(CategoryDO category);

    /**
     * 修改分类
     *
     * @param category 分类
     * @return 结果
     */
    int updateCategory(CategoryDO category);

    /**
     * 批量删除分类
     *
     * @param ids 需要删除的分类主键集合
     * @return 结果
     */
    int deleteCategoryByIds(Long[] ids);

    /**
     * 删除分类信息
     *
     * @param id 分类主键
     * @return 结果
     */
    int deleteCategoryById(Long id);
}
