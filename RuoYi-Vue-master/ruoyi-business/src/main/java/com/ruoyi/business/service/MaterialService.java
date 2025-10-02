package com.ruoyi.business.service;

/**
 * Material Service
 *
 * @author xcom
 * @date 2025/10/2
 */

import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Material;
import com.ruoyi.business.domain.model.MaterialDetailVO;

import java.util.List;

/**
 * 物资Service接口
 *
 * @author xcom
 * @date 2025-10-02
 */
public interface MaterialService {
    /**
     * 查询物资
     *
     * @param id 物资主键
     * @return 物资
     */
    MaterialDO selectMaterialById(Long id);

    /**
     * 查询分类
     *
     * @param ids 分类主键
     * @return 分类
     */
    List<MaterialDO> selectMaterialByIds(List<Long> ids);

    /**
     * 查询物资列表
     *
     * @param material 物资
     * @return 物资集合
     */
    List<MaterialDO> selectMaterialList(Material material);

    /**
     * 查询物资列表
     *
     * @param material 物资
     * @return 物资集合
     */
    List<MaterialDetailVO> selectMaterialDetailList(Material material);

    /**
     * 新增物资
     *
     * @param material 物资
     * @return 结果
     */
    int insertMaterial(MaterialDO material);

    /**
     * 修改物资
     *
     * @param material 物资
     * @return 结果
     */
    int updateMaterial(MaterialDO material);

    /**
     * 批量删除物资
     *
     * @param ids 需要删除的物资主键集合
     * @return 结果
     */
    int deleteMaterialByIds(Long[] ids);

    /**
     * 删除物资信息
     *
     * @param id 物资主键
     * @return 结果
     */
    int deleteMaterialById(Long id);

    /**
     * 新增不存在的物资信息，
     * 返回物资信息id
     * @param material
     * @return
     */
    Long insertNotExistMaterial(MaterialDO material);

}