package com.ruoyi.business.service;

import java.util.List;

import com.ruoyi.business.domain.entity.OriginalAssetDO;
import com.ruoyi.business.domain.model.OriginalAsset;
import com.ruoyi.business.domain.model.request.AssetBordReqBO;
import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.domain.model.response.OriginalAssetDetailVO;

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
     * @param projectId
     * @param originalCodes 
     * @return 原始资产集合
     */
    List<OriginalAssetDO> selectOriginalAssetList(Long projectId, List<String> originalCodes);

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

    /**
     * 更新原始编码统计信息
     * @param projectId
     * @param originalCode
     * @return
     */
    boolean updateMatchStatic(Long projectId, String originalCode);

    /**
     * 账务资产报表 概览
     * @param reqBO
     * @return
     */
    AssetMetricsVO getLedgerOverview(AssetBordReqBO reqBO);

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

    /**
     * 分页获取分类指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listCategoryMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取品牌指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listBrandMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取名称指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listAssetNameMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取型号指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listSpecificationMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取取得时间指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listObtainTimeMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取地点指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listLocationMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取管理部门指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listManagedDeptMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取使用部门指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listUsingDeptMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取管理人指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listManagedEmpMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取使用人指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listUsingEmpMetrics(AssetCheckMetricsReqBO reqBO);

    /**
     * 获取所有id
     * @param lastId
     * @param size
     * @return
     */
    List<Long> listAllIds(Long lastId, Integer size);
}