package com.ruoyi.business.service.impl;

import com.ruoyi.business.convert.MaterialConvert;
import com.ruoyi.business.domain.entity.BrandDO;
import com.ruoyi.business.domain.entity.MaterialDO;
import com.ruoyi.business.domain.model.Material;
import com.ruoyi.business.domain.model.MaterialDetailVO;
import com.ruoyi.business.mapper.MaterialMapper;
import com.ruoyi.business.service.MaterialService;
import com.ruoyi.common.exception.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 物资Service业务层处理
 *
 * @author xcom
 * @date 2025-10-02
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Resource
    private MaterialMapper materialMapper;

    /**
     * 查询物资
     *
     * @param id 物资主键
     * @return 物资
     */
    @Override
    public MaterialDO selectMaterialById(Long id) {
        return materialMapper.selectMaterialById(id);
    }

    /**
     * 查询物资
     *
     * @param ids 物资主键
     * @return 物资
     */
    @Override
    public List<MaterialDO> selectMaterialByIds(List<Long> ids) {
        return materialMapper.selectMaterialByIds(ids);
    }

    /**
     * 查询物资列表
     *
     * @param material 物资
     * @return 物资
     */
    @Override
    public List<MaterialDO> selectMaterialList(Material material) {
        return materialMapper.selectMaterialList(material);
    }

    /**
     * 查询物资列表
     *
     * @param material 物资
     * @return 物资
     */
    @Override
    public List<MaterialDetailVO> selectMaterialDetailList(Material material) {
        return materialMapper.selectMaterialDetailList(material);
    }

    /**
     * 新增物资
     *
     * @param material 物资
     * @return 结果
     */
    @Override
    public int insertMaterial(MaterialDO material) {
        return materialMapper.insertMaterial(material);
    }

    /**
     * 修改物资
     *
     * @param material 物资
     * @return 结果
     */
    @Override
    public int updateMaterial(MaterialDO material) {
        return materialMapper.updateMaterial(material);
    }

    /**
     * 批量删除物资
     *
     * @param ids 需要删除的物资主键
     * @return 结果
     */
    @Override
    public int deleteMaterialByIds(Long[] ids) {
        return materialMapper.deleteMaterialByIds(ids);
    }

    /**
     * 删除物资信息
     *
     * @param id 物资主键
     * @return 结果
     */
    @Override
    public int deleteMaterialById(Long id) {
        return materialMapper.deleteMaterialById(id);
    }

    @Override
    public Long insertNotExistMaterial(MaterialDO material) {
        Material material1 = MaterialConvert.INSTANCE.toMaterial(material);
        List<MaterialDO> materialList = this.selectMaterialList(material1);
        if (CollectionUtils.isEmpty(materialList)) {
            // 创建商品
            this.insertMaterial(material);
            return material.getId();
        } else {
            if (materialList.size() > 1) {
                throw new ServiceException("物资商品存在多个，数据异常");
            }
            MaterialDO existMaterial = materialList.get(0);
            return existMaterial.getId();
        }
    }
}