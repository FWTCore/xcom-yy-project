package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.PaAnalysisConfigDetailDO;
import com.ruoyi.business.domain.model.PaAnalysisConfigDetail;

import java.util.List;

/**
 * PA平台分析配置详情;PA平台分析配置详情Service接口
 *
 * @author xcom
 * @date 2026/1/3
 */

public interface PaAnalysisConfigDetailService {
    /**
     * 查询PA平台分析配置详情;PA平台分析配置详情
     *
     * @param id PA平台分析配置详情;PA平台分析配置详情主键
     * @return PA平台分析配置详情;PA平台分析配置详情
     */
    PaAnalysisConfigDetailDO selectPaAnalysisConfigDetailById(Long id);

    /**
     * 查询PA平台分析配置详情;PA平台分析配置详情列表
     *
     * @param paAnalysisConfigDetail PA平台分析配置详情;PA平台分析配置详情
     * @return PA平台分析配置详情;PA平台分析配置详情集合
     */
    List<PaAnalysisConfigDetailDO> selectPaAnalysisConfigDetailList(PaAnalysisConfigDetail paAnalysisConfigDetail);

    /**
     * 新增PA平台分析配置详情;PA平台分析配置详情
     *
     * @param paAnalysisConfigDetail PA平台分析配置详情;PA平台分析配置详情
     * @return 结果
     */
    int insertPaAnalysisConfigDetail(PaAnalysisConfigDetail paAnalysisConfigDetail);

    /**
     * 修改PA平台分析配置详情;PA平台分析配置详情
     *
     * @param paAnalysisConfigDetail PA平台分析配置详情;PA平台分析配置详情
     * @return 结果
     */
    int updatePaAnalysisConfigDetail(PaAnalysisConfigDetail paAnalysisConfigDetail);

    /**
     * 批量删除PA平台分析配置详情;PA平台分析配置详情
     *
     * @param ids 需要删除的PA平台分析配置详情;PA平台分析配置详情主键集合
     * @return 结果
     */
    int deletePaAnalysisConfigDetailByIds(Long[] ids);

    /**
     * 删除PA平台分析配置详情;PA平台分析配置详情信息
     *
     * @param id PA平台分析配置详情;PA平台分析配置详情主键
     * @return 结果
     */
    int deletePaAnalysisConfigDetailById(Long id);
}
