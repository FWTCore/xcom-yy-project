package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.HomeAssetStatsVO;
import com.ruoyi.business.model.request.AssetCopyReqBO;

import java.util.List;

/**
 * Asset Service
 *
 * @author xcom
 * @date 2025/9/20
 */

public interface AssetService {

    /**
     * 后去首页资产统计
     *
     * @param projectId
     * @return
     */
    HomeAssetStatsVO getHomeAssetStats(Long projectId);
    /**
     * 查询资产
     *
     * @param id 资产主键
     * @return 资产
     */
    AssetDO selectAssetById(Long id);

    /**
     * 查询分类
     *
     * @param ids  资产主键
     * @return 分类
     */
    List<AssetDO> selectAssetByIds(List<Long> ids);

    /**
     * 查询资产列表
     *
     * @param asset 资产
     * @return 资产集合
     */
    List<AssetDO> selectAssetList(Asset asset);

    /**
     * 新增资产
     *
     * @param asset 资产
     * @return 结果
     */
    int insertAsset(AssetDO asset);

    /**
     * 修改资产
     *
     * @param asset 资产
     * @return 结果
     */
    int updateAsset(AssetDO asset);

    /**
     * 批量删除资产
     *
     * @param ids 需要删除的资产主键集合
     * @return 结果
     */
    int deleteAssetByIds(Long[] ids);

    /**
     * 删除资产信息
     *
     * @param id 资产主键
     * @return 结果
     */
    int deleteAssetById(Long id);

    /**
     * copy 资产
     * @param copyReqBO
     * @return
     */
    Boolean copyData(AssetCopyReqBO copyReqBO);
}
