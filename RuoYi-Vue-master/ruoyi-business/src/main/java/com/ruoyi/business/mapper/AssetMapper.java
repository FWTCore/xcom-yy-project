package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.AssetDO;
import com.ruoyi.business.domain.model.Asset;
import com.ruoyi.business.domain.model.request.AssetCheckMetricsReqBO;
import com.ruoyi.business.domain.model.request.CollectionStatsReqBO;
import com.ruoyi.business.domain.model.response.AssetMetricsVO;
import com.ruoyi.business.domain.model.response.AssetDetailVO;
import com.ruoyi.business.domain.model.response.CollectionStatsVO;
import com.ruoyi.business.domain.model.response.HomeAssetStatsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资产;(asset)表数据库访问层
 *
 * @author :xcom
 * @date : 2025-9-18
 */
@Mapper
public interface AssetMapper {

    /**
     * 后去首页资产统计
     *
     * @param projectId
     * @return
     */
    HomeAssetStatsVO getHomeAssetStats(@Param("projectId") Long projectId);

    /**
     * 查询资产
     *
     * @param id 资产主键
     * @return 资产
     */
    AssetDO selectAssetById(Long id);

    /**
     * 查询资产
     *
     * @param ids 资产主键
     * @return 资产
     */
    List<AssetDO> selectAssetByIds(@Param("ids") List<Long> ids);

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
     * 删除资产
     *
     * @param id 资产主键
     * @return 结果
     */
    int deleteAssetById(Long id);

    /**
     * 批量删除资产
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteAssetByIds(Long[] ids);

    /**
     * 按采集人统计
     * @param reqBO
     * @return
     */
    List<CollectionStatsVO> selectDimensionStatsForUser(CollectionStatsReqBO reqBO);

    /**
     * 按使用部门统计
     * @param reqBO
     * @return
     */
    List<CollectionStatsVO> selectDimensionStatsForUsingEmp(CollectionStatsReqBO reqBO);

    /**
     * 按存放地点统计
     * @param reqBO
     * @return
     */
    List<CollectionStatsVO> selectDimensionStatsForLocation(CollectionStatsReqBO reqBO);

    /**
     * 统计数据橄榄
     * @param deptId
     * @param projectId
     * @return
     */
    AssetMetricsVO getPhysicalOverview(@Param("deptId") Long deptId, @Param("projectId") Long projectId);

    /**
     * 获取名称指标
     * @param projectId
     * @param assetName
     * @return
     */
    AssetMetricsVO getAssetNameMetrics(@Param("projectId") Long projectId, @Param("assetName") String assetName);

    /**
     * 获取使用部门
     * @param projectId
     * @param usingDeptId
     * @return
     */
    AssetMetricsVO getUsingDeptMetrics(@Param("projectId") Long projectId, @Param("usingDeptId") Long usingDeptId);

    /**
     * 获取存放地点
     * @param projectId
     * @param locationId
     * @return
     */
    AssetMetricsVO getLocationMetrics(@Param("projectId") Long projectId, @Param("locationId") Long locationId);

    /**
     * 分页获取分类指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listCategoryMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取品牌指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listBrandMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取名称指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listAssetNameMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取型号指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listSpecificationMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取地点指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listLocationMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取管理部门指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listManagedDeptMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取使用部门指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listUsingDeptMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取管理人指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listManagedEmpMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 分页获取使用人指标
     * @param reqBO
     * @return
     */
    List<AssetMetricsVO> listUsingEmpMetrics(@Param("reqBO") AssetCheckMetricsReqBO reqBO);

    /**
     * 统计原始编码数据
     *
     * @param projectId
     * @param originalCode
     * @return
     */
    int getMatchStatic(@Param("projectId") Long projectId, @Param("originalCode") String originalCode);

}