package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.VerifyAssetDO;
import com.ruoyi.business.domain.model.VerifyAsset;
import com.ruoyi.business.domain.model.response.VerifyAssetDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 核实资产Mapper接口
 *
 * @author xcom
 * @date 2025/12/21
 */

@Mapper
public interface VerifyAssetMapper {
    /**
     * 查询核实资产
     *
     * @param id 核实资产主键
     * @return 核实资产
     */
    VerifyAssetDO selectVerifyAssetById(Long id);

    /**
     * 查询核实资产
     *
     * @param ids 核实资产主键
     * @return 核实资产
     */
    List<VerifyAssetDO> selectVerifyAssetByIds(List<Long> ids);

    /**
     * 查询核实资产列表
     *
     * @param verifyAsset 核实资产
     * @return 核实资产集合
     */
    List<VerifyAssetDO> selectVerifyAssetList(VerifyAsset verifyAsset);

    /**
     * 查询核实资产列表
     *
     * @param verifyAsset 核实资产
     * @return 核实资产集合
     */
    List<VerifyAssetDetailVO> selectVerifyAssetDetailList(VerifyAsset verifyAsset);

    /**
     * 新增核实资产
     *
     * @param verifyAsset 核实资产
     * @return 结果
     */
    int insertVerifyAsset(VerifyAssetDO verifyAsset);

    /**
     * 修改核实资产
     *
     * @param verifyAsset 核实资产
     * @return 结果
     */
    int updateVerifyAsset(VerifyAssetDO verifyAsset);

    /**
     * 删除核实资产
     *
     * @param id 核实资产主键
     * @return 结果
     */
    int deleteVerifyAssetById(Long id);

    /**
     * 批量删除核实资产
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteVerifyAssetByIds(Long[] ids);

    /**
     * 盘点资产
     * @param projectId
     * @return
     */
    int syncDeleteAsset(@Param("projectId") Long projectId);
    /**
     * 盘点资产
     * @param projectId
     * @return
     */
    int syncInsertAsset(@Param("projectId") Long projectId);

    /**
     * 盘点资产
     * @param projectId
     * @return
     */
    int syncUpdateAsset(@Param("projectId") Long projectId);

    /**
     * 原始资产
     * @param projectId
     * @return
     */
    int syncDeleteOriginalAsset(@Param("projectId") Long projectId);
    /**
     * 原始资产
     * @param projectId
     * @return
     */
    int syncInsertOriginalAsset(@Param("projectId") Long projectId);

    /**
     * 原始资产
     * @param projectId
     * @return
     */
    int syncUpdateOriginalAsset(@Param("projectId") Long projectId);

}
