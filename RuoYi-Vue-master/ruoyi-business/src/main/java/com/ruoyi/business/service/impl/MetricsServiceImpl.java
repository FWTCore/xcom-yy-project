package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.MetricsDO;
import com.ruoyi.business.domain.model.Metrics;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.mapper.MetricsMapper;
import com.ruoyi.business.service.MetricsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * MetricsService
 *
 * @author xcom
 * @date 2025/11/28
 */

@Service
public class MetricsServiceImpl implements MetricsService {
    @Resource
    private MetricsMapper metricsMapper;

    /**
     * 查询指标
     *
     * @param id 指标主键
     * @return 指标
     */
    @Override
    public MetricsDO selectMetricsById(Long id) {
        return metricsMapper.selectMetricsById(id);
    }

    /**
     * 查询指标
     *
     * @param ids 指标主键
     * @return 指标
     */
    @Override
    public List<MetricsDO> selectMetricsByIds(List<Long> ids) {
        return metricsMapper.selectMetricsByIds(ids);
    }

    /**
     * 查询指标列表
     *
     * @param metrics 指标
     * @return 指标
     */
    @Override
    public List<MetricsDO> selectMetricsList(Metrics metrics) {
        return metricsMapper.selectMetricsList(metrics);
    }

    /**
     * 新增指标
     *
     * @param metrics 指标
     * @return 结果
     */
    @Override
    public int insertMetrics(MetricsDO metrics) {
        return metricsMapper.insertMetrics(metrics);
    }

    /**
     * 修改指标
     *
     * @param metrics 指标
     * @return 结果
     */
    @Override
    public int updateMetrics(MetricsDO metrics) {
        return metricsMapper.updateMetrics(metrics);
    }

    /**
     * 批量删除指标
     *
     * @param ids 需要删除的指标主键
     * @return 结果
     */
    @Override
    public int deleteMetricsByIds(Long[] ids) {
        return metricsMapper.deleteMetricsByIds(ids);
    }

    /**
     * 删除指标信息
     *
     * @param id 指标主键
     * @return 结果
     */
    @Override
    public int deleteMetricsById(Long id) {
        return metricsMapper.deleteMetricsById(id);
    }

    @Override
    public List<AssetMetricsVO> listPhysicalBordMetrics(AssetBordReqBO assetBordReqBO) {
        return metricsMapper.listPhysicalBordMetrics(assetBordReqBO);
    }

    @Override
    public List<AssetMetricsVO> listLedgerBordMetrics(AssetBordReqBO assetBordReqBO) {
        return metricsMapper.listLedgerBordMetrics(assetBordReqBO);
    }
}
