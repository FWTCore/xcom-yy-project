package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.request.AssetBatchUpdateReqBO;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.request.CollectionStatsReqBO;
import com.ruoyi.business.domain.model.response.AssetDetailVO;
import com.ruoyi.business.domain.model.response.CollectionStatsVO;
import com.ruoyi.business.domain.model.response.HomeAssetStatsVO;
import com.ruoyi.business.domain.model.request.AssetCopyReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;

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
     * 查询资产列表
     *
     * @param asset 资产
     * @return 资产集合
     */
    List<AssetDetailVO> selectAssetDetailList(Asset asset);

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
     * 新增、编辑资产
     * @param asset
     * @return
     */
    int upsetAsset(AssetDO asset);

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

    /**
     * 查询资产
     *
     * @param code 资产编码
     * @param projectId 项目id
     * @return 资产
     */
    AssetDetailVO selectAssetByCode(String code, Long projectId);

    /**
     * 查询维度统计
     * @param reqBO
     * @return
     */
    List<CollectionStatsVO> selectCollectionStatsForDimension(CollectionStatsReqBO reqBO);

    /**
     * 批量修改资产
     * @param batchUpdateReqBO
     * @return
     */
    Boolean batchUpdate(AssetBatchUpdateReqBO batchUpdateReqBO);

    /**
     * 实物资产报表 概览
     * @param reqBO
     * @return
     */
    AssetMetricsVO getPhysicalOverview(AssetBordReqBO reqBO);

    /**
     * 获取名称指标
     * @param projectId
     * @param assetName
     * @return
     */
    AssetMetricsVO getAssetNameMetrics(Long projectId, String assetName);

    /**
     * 获取使用部门
     * @param projectId
     * @param usingDeptId
     * @return
     */
    AssetMetricsVO getUsingDeptMetrics(Long projectId, Long usingDeptId);

    /**
     * 获取存放地点
     * @param projectId
     * @param locationId
     * @return
     */
    AssetMetricsVO getLocationMetrics(Long projectId, Long locationId);

}
