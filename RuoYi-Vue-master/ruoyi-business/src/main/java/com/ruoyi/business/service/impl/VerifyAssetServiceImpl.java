package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.VerifyAssetDO;
import com.ruoyi.business.domain.model.VerifyAsset;
import com.ruoyi.business.domain.model.response.VerifyAssetDetailVO;
import com.ruoyi.business.mapper.VerifyAssetMapper;
import com.ruoyi.business.service.VerifyAssetService;
import com.ruoyi.common.annotation.DataScope;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 核实资产Service业务层处理
 *
 * @author xcom
 * @date 2025/12/21
 */

@Service
public class VerifyAssetServiceImpl implements VerifyAssetService {

    @Resource
    private VerifyAssetMapper verifyAssetMapper;

    /**
     * 查询核实资产
     *
     * @param id 核实资产主键
     * @return 核实资产
     */
    @Override
    public VerifyAssetDO selectVerifyAssetById(Long id) {
        return verifyAssetMapper.selectVerifyAssetById(id);
    }

    /**
     * 查询核实资产
     *
     * @param ids 核实资产主键
     * @return 核实资产
     */
    @Override
    public List<VerifyAssetDO> selectVerifyAssetByIds(List<Long> ids) {
        return verifyAssetMapper.selectVerifyAssetByIds(ids);
    }

    /**
     * 查询核实资产列表
     *
     * @param verifyAsset 核实资产
     * @return 核实资产
     */
    @Override
    public List<VerifyAssetDO> selectVerifyAssetList(VerifyAsset verifyAsset) {
        return verifyAssetMapper.selectVerifyAssetList(verifyAsset);
    }

    @Override
    @DataScope(deptAlias = "a", projectAlias = "a")
    public List<VerifyAssetDetailVO> selectVerifyAssetDetailList(VerifyAsset verifyAsset) {
        return verifyAssetMapper.selectVerifyAssetDetailList(verifyAsset);
    }

    /**
     * 新增核实资产
     *
     * @param verifyAsset 核实资产
     * @return 结果
     */
    @Override
    public int insertVerifyAsset(VerifyAssetDO verifyAsset) {
        return verifyAssetMapper.insertVerifyAsset(verifyAsset);
    }

    /**
     * 修改核实资产
     *
     * @param verifyAsset 核实资产
     * @return 结果
     */
    @Override
    public int updateVerifyAsset(VerifyAssetDO verifyAsset) {
        return verifyAssetMapper.updateVerifyAsset(verifyAsset);
    }

    /**
     * 批量删除核实资产
     *
     * @param ids 需要删除的核实资产主键
     * @return 结果
     */
    @Override
    public int deleteVerifyAssetByIds(Long[] ids) {
        return verifyAssetMapper.deleteVerifyAssetByIds(ids);
    }

    /**
     * 删除核实资产信息
     *
     * @param id 核实资产主键
     * @return 结果
     */
    @Override
    public int deleteVerifyAssetById(Long id) {
        return verifyAssetMapper.deleteVerifyAssetById(id);
    }

    @Override
    public boolean syncVerifyAssetForPhysical(Long projectId) {
        if (ObjectUtils.isEmpty(projectId)) {
            return false;
        }
        verifyAssetMapper.syncDeleteAsset(projectId);
//        verifyAssetMapper.syncUpdateAsset(projectId);
        verifyAssetMapper.syncInsertAsset(projectId);
        return true;
    }

    @Override
    public boolean syncVerifyAssetForLedger(Long projectId) {
        if (ObjectUtils.isEmpty(projectId)) {
            return false;
        }
        verifyAssetMapper.syncDeleteOriginalAsset(projectId);
//        verifyAssetMapper.syncUpdateOriginalAsset(projectId);
        verifyAssetMapper.syncInsertOriginalAsset(projectId);
        return true;
    }
}
