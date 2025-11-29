package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.MetricsDO;
import com.ruoyi.business.domain.model.Metrics;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;

import java.util.List;

/**
 * MetricsService
 *
 * @author xcom
 * @date 2025/11/28
 */

public interface MetricsService {
    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    MetricsDO selectMetricsById(Long id);

    /**
     * 查询分类
     *
     * @param ids  指标主键
     * @return 分类
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
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键集合
     * @return 结果
     */
    int deleteMetricsByIds(Long[] ids);

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    int deleteMetricsById(Long id);


    /**
     * 查询资产指标
     * @param assetBordReqBO
     * @return
     */
    List<AssetMetricsVO> listPhysicalBordMetrics(AssetBordReqBO assetBordReqBO);
    /**
     * 查询资产指标
     * @param assetBordReqBO
     * @return
     */
    List<AssetMetricsVO> listLedgerBordMetrics(AssetBordReqBO assetBordReqBO);
}
