package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.PaAnalysisConfigDO;
import com.ruoyi.business.domain.model.PaAnalysisConfig;
import com.ruoyi.business.mapper.PaAnalysisConfigMapper;
import com.ruoyi.business.service.PaAnalysisConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PA平台分析配置;PA平台分析配置Service业务层处理
 *
 * @author xcom
 * @date 2026/1/3
 */

@Service
public class PaAnalysisConfigServiceImpl implements PaAnalysisConfigService {
    @Resource
    private PaAnalysisConfigMapper paAnalysisConfigMapper;

    /**
     * 查询PA平台分析配置;PA平台分析配置
     *
     * @param id PA平台分析配置;PA平台分析配置主键
     * @return PA平台分析配置;PA平台分析配置
     */
    @Override
    public PaAnalysisConfigDO selectPaAnalysisConfigById(Long id) {
        return paAnalysisConfigMapper.selectPaAnalysisConfigById(id);
    }

    /**
     * 查询PA平台分析配置;PA平台分析配置列表
     *
     * @param paAnalysisConfig PA平台分析配置;PA平台分析配置
     * @return PA平台分析配置;PA平台分析配置
     */
    @Override
    public List<PaAnalysisConfigDO> selectPaAnalysisConfigList(PaAnalysisConfig paAnalysisConfig) {
        return paAnalysisConfigMapper.selectPaAnalysisConfigList(paAnalysisConfig);
    }

    /**
     * 新增PA平台分析配置;PA平台分析配置
     *
     * @param paAnalysisConfig PA平台分析配置;PA平台分析配置
     * @return 结果
     */
    @Override
    public int insertPaAnalysisConfig(PaAnalysisConfig paAnalysisConfig) {
        return paAnalysisConfigMapper.insertPaAnalysisConfig(paAnalysisConfig);
    }

    /**
     * 修改PA平台分析配置;PA平台分析配置
     *
     * @param paAnalysisConfig PA平台分析配置;PA平台分析配置
     * @return 结果
     */
    @Override
    public int updatePaAnalysisConfig(PaAnalysisConfig paAnalysisConfig) {
        return paAnalysisConfigMapper.updatePaAnalysisConfig(paAnalysisConfig);
    }

    /**
     * 批量删除PA平台分析配置;PA平台分析配置
     *
     * @param ids 需要删除的PA平台分析配置;PA平台分析配置主键
     * @return 结果
     */
    @Override
    public int deletePaAnalysisConfigByIds(Long[] ids) {
        return paAnalysisConfigMapper.deletePaAnalysisConfigByIds(ids);
    }

    /**
     * 删除PA平台分析配置;PA平台分析配置信息
     *
     * @param id PA平台分析配置;PA平台分析配置主键
     * @return 结果
     */
    @Override
    public int deletePaAnalysisConfigById(Long id) {
        return paAnalysisConfigMapper.deletePaAnalysisConfigById(id);
    }
}
