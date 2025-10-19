package com.ruoyi.business.service;

import java.util.List;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.business.domain.model.OriginalAssetDetailVO;

/**
 * 原始资产Service接口
 *
 * @author xcom
 * @date 2025-10-19
 */
public interface OriginalAssetService {
    /**
     * 查询原始资产
     *
     * @param id 原始资产主键
     * @return 原始资产
     */
    OriginalAssetDO selectOriginalAssetById(Long id);

    /**
     * 查询分类
     *
     * @param ids  原始资产主键
     * @return 分类
     */
    List<OriginalAssetDO> selectOriginalAssetByIds(List<Long> ids);

    /**
     * 查询原始资产列表
     *
     * @param originalAsset 原始资产
     * @return 原始资产集合
     */
    List<OriginalAssetDO> selectOriginalAssetList(OriginalAsset originalAsset);

    /**
     * 查询原始资产列表
     *
     * @param originalAsset 原始资产
     * @return 原始资产集合
     */
    List<OriginalAssetDetailVO> selectOriginalAssetDetailList(OriginalAsset originalAsset);

    /**
     * 新增原始资产
     *
     * @param originalAsset 原始资产
     * @return 结果
     */
    int insertOriginalAsset(OriginalAssetDO originalAsset);

    /**
     * 修改原始资产
     *
     * @param originalAsset 原始资产
     * @return 结果
     */
    int updateOriginalAsset(OriginalAssetDO originalAsset);

    /**
     * 批量删除原始资产
     *
     * @param ids 需要删除的原始资产主键集合
     * @return 结果
     */
    int deleteOriginalAssetByIds(Long[] ids);

    /**
     * 删除原始资产信息
     *
     * @param id 原始资产主键
     * @return 结果
     */
    int deleteOriginalAssetById(Long id);

    /**
     * 导入数据
     *
     * @param dataList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    String importData(List<OriginalAsset> dataList, Boolean isUpdateSupport);
}