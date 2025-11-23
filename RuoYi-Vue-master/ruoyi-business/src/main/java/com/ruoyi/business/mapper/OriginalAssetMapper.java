package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.business.domain.model.response.AssetBordMetricsVO;
import com.ruoyi.business.domain.model.response.OriginalAssetDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 原始资产;(original_asset)表数据库访问层
 * @author :xcom
 * @date : 2025-10-19
 */
@Mapper
public interface OriginalAssetMapper {
    /**
     * 查询原始资产
     *
     * @param id 原始资产主键
     * @return 原始资产
     */
    OriginalAssetDO selectOriginalAssetById(Long id);

    /**
     * 查询原始资产
     *
     * @param ids 原始资产主键
     * @return 原始资产
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
     * 删除原始资产
     *
     * @param id 原始资产主键
     * @return 结果
     */
    int deleteOriginalAssetById(Long id);

    /**
     * 批量删除原始资产
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteOriginalAssetByIds(Long[] ids);

    /**
     * 更新原始编码统计信息
     * @param originalCode
     * @return
     */
    boolean updateMatchStatic(String originalCode);

    /**
     * 统计数据橄榄
     * @param deptId
     * @param projectId
     * @return
     */
    AssetBordMetricsVO getLedgerOverview(@Param("deptId") Long deptId, @Param("projectId") Long projectId);
}
