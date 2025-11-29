package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.MetricsDO;
import com.ruoyi.business.domain.model.Metrics;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.request.AssetCheckBO;
import com.ruoyi.business.domain.model.response.AssetDataDetailVO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.domain.model.response.OriginalAssetDataDetailVO;

import java.util.List;

/**
 * 指标Mapper接口
 *
 * @author xcom
 * @date 2025-11-28
 */
public interface MetricsMapper {
    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    MetricsDO selectMetricsById(Long id);

    /**
     * 查询指标
     *
     * @param ids 指标主键
     * @return 指标
     */
    List<MetricsDO> selectMetricsByIds(List<Long> ids);

    /**
     * 查询指标列表
     *
     * @param metrics 指标
     * @return 指标集合
     */
    List<MetricsDO> selectMetricsList(Metrics metrics);

    /**
     * 新增指标
     *
     * @param metrics 指标
     * @return 结果
     */
    int insertMetrics(MetricsDO metrics);

    /**
     * 修改指标
     *
     * @param metrics 指标
     * @return 结果
     */
    int updateMetrics(MetricsDO metrics);

    /**
     * 删除指标
     *
     * @param id 指标主键
     * @return 结果
     */
    int deleteMetricsById(Long id);

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteMetricsByIds(Long[] ids);

    /**
     * 获取实物名称指标
     * @param assetBordReqBO
     * @return
     */
    List<AssetMetricsVO> listPhysicalBordMetrics(AssetBordReqBO assetBordReqBO);

    /**
     * 获取实物名称指标
     * @param assetBordReqBO
     * @return
     */
    List<AssetMetricsVO> listLedgerBordMetrics(AssetBordReqBO assetBordReqBO);

    /**
     * 获取实物资产列表
     * @param assetCheckBO
     * @return
     */
    List<AssetDataDetailVO> listPhysical(AssetCheckBO assetCheckBO);

    /**
     * 获取实物资产列表
     * @param assetCheckBO
     * @return
     */
    List<OriginalAssetDataDetailVO> listLedger(AssetCheckBO assetCheckBO);

}