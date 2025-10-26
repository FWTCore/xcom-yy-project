package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Material;
import com.ruoyi.business.model.response.MaterialDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 物资;(material)表数据库访问层
 *
 * @author :xcom
 * @date : 2025-9-18
 */
@Mapper
public interface MaterialMapper {
    /**
     * 查询物资
     *
     * @param id 物资主键
     * @return 物资
     */
    MaterialDO selectMaterialById(Long id);

    /**
     * 查询物资
     *
     * @param ids 物资主键
     * @return 物资
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
     * 删除物资
     *
     * @param id 物资主键
     * @return 结果
     */
    int deleteMaterialById(Long id);

    /**
     * 批量删除物资
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMaterialByIds(Long[] ids);
}