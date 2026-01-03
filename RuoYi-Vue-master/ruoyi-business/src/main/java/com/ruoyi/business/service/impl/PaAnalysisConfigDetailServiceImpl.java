package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.PaAnalysisConfigDetailDO;
import com.ruoyi.business.domain.model.PaAnalysisConfigDetail;
import com.ruoyi.business.mapper.PaAnalysisConfigDetailMapper;
import com.ruoyi.business.service.PaAnalysisConfigDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PA平台分析配置详情;PA平台分析配置详情Service业务层处理
 *
 * @author xcom
 * @date 2026/1/3
 */

@Service
public class PaAnalysisConfigDetailServiceImpl implements PaAnalysisConfigDetailService {
    @Resource
    private PaAnalysisConfigDetailMapper paAnalysisConfigDetailMapper;

    /**
     * 查询PA平台分析配置详情;PA平台分析配置详情
     *
     * @param id PA平台分析配置详情;PA平台分析配置详情主键
     * @return PA平台分析配置详情;PA平台分析配置详情
     */
    @Override
    public PaAnalysisConfigDetailDO selectPaAnalysisConfigDetailById(Long id) {
        return paAnalysisConfigDetailMapper.selectPaAnalysisConfigDetailById(id);
    }

    /**
     * 查询PA平台分析配置详情;PA平台分析配置详情列表
     *
     * @param paAnalysisConfigDetail PA平台分析配置详情;PA平台分析配置详情
     * @return PA平台分析配置详情;PA平台分析配置详情
     */
    @Override
    public List<PaAnalysisConfigDetailDO> selectPaAnalysisConfigDetailList(PaAnalysisConfigDetail paAnalysisConfigDetail) {
        return paAnalysisConfigDetailMapper.selectPaAnalysisConfigDetailList(paAnalysisConfigDetail);
    }

    /**
     * 新增PA平台分析配置详情;PA平台分析配置详情
     *
     * @param paAnalysisConfigDetail PA平台分析配置详情;PA平台分析配置详情
     * @return 结果
     */
    @Override
    public int insertPaAnalysisConfigDetail(PaAnalysisConfigDetail paAnalysisConfigDetail) {
        return paAnalysisConfigDetailMapper.insertPaAnalysisConfigDetail(paAnalysisConfigDetail);
    }

    /**
     * 修改PA平台分析配置详情;PA平台分析配置详情
     *
     * @param paAnalysisConfigDetail PA平台分析配置详情;PA平台分析配置详情
     * @return 结果
     */
    @Override
    public int updatePaAnalysisConfigDetail(PaAnalysisConfigDetail paAnalysisConfigDetail) {
        return paAnalysisConfigDetailMapper.updatePaAnalysisConfigDetail(paAnalysisConfigDetail);
    }

    /**
     * 批量删除PA平台分析配置详情;PA平台分析配置详情
     *
     * @param ids 需要删除的PA平台分析配置详情;PA平台分析配置详情主键
     * @return 结果
     */
    @Override
    public int deletePaAnalysisConfigDetailByIds(Long[] ids) {
        return paAnalysisConfigDetailMapper.deletePaAnalysisConfigDetailByIds(ids);
    }

    /**
     * 删除PA平台分析配置详情;PA平台分析配置详情信息
     *
     * @param id PA平台分析配置详情;PA平台分析配置详情主键
     * @return 结果
     */
    @Override
    public int deletePaAnalysisConfigDetailById(Long id) {
        return paAnalysisConfigDetailMapper.deletePaAnalysisConfigDetailById(id);
    }
}
