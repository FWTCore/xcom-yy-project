package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.VerifyAssetDO;
import com.ruoyi.business.domain.model.VerifyAsset;
import com.ruoyi.business.domain.model.response.VerifyAssetDetailVO;

import java.util.List;

/**
 * 核实资产Service接口
 *
 * @author xcom
 * @date 2025/12/21
 */

public interface VerifyAssetService {
    /**
     * 查询核实资产
     *
     * @param id 核实资产主键
     * @return 核实资产
     */
    VerifyAssetDO selectVerifyAssetById(Long id);

    /**
     * 查询分类
     *
     * @param ids  核实资产主键
     * @return 分类
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
     * 批量删除核实资产
     *
     * @param ids 需要删除的核实资产主键集合
     * @return 结果
     */
    int deleteVerifyAssetByIds(Long[] ids);

    /**
     * 删除核实资产信息
     *
     * @param id 核实资产主键
     * @return 结果
     */
    int deleteVerifyAssetById(Long id);

    /**
     * 同步核实资产数据
     * @param projectId
     * @return
     */
    boolean syncVerifyAsset(Long projectId);

}
