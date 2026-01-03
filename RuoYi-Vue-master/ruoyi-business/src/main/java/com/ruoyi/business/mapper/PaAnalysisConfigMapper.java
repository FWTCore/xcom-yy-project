package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.PaAnalysisConfigDO;
import com.ruoyi.business.domain.model.PaAnalysisConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PA平台分析配置;PA平台分析配置Mapper接口
 *
 * @author xcom
 * @date 2026/1/2
 */

@Mapper
public interface PaAnalysisConfigMapper {
    /**
     * 查询PA平台分析配置;PA平台分析配置
     *
     * @param id PA平台分析配置;PA平台分析配置主键
     * @return PA平台分析配置;PA平台分析配置
     */
    PaAnalysisConfigDO selectPaAnalysisConfigById(Long id);

    /**
     * 查询PA平台分析配置;PA平台分析配置列表
     *
     * @param paAnalysisConfig PA平台分析配置;PA平台分析配置
     * @return PA平台分析配置;PA平台分析配置集合
     */
    List<PaAnalysisConfigDO> selectPaAnalysisConfigList(PaAnalysisConfig paAnalysisConfig);

    /**
     * 新增PA平台分析配置;PA平台分析配置
     *
     * @param paAnalysisConfig PA平台分析配置;PA平台分析配置
     * @return 结果
     */
    int insertPaAnalysisConfig(PaAnalysisConfig paAnalysisConfig);

    /**
     * 修改PA平台分析配置;PA平台分析配置
     *
     * @param paAnalysisConfig PA平台分析配置;PA平台分析配置
     * @return 结果
     */
    int updatePaAnalysisConfig(PaAnalysisConfig paAnalysisConfig);

    /**
     * 删除PA平台分析配置;PA平台分析配置
     *
     * @param id PA平台分析配置;PA平台分析配置主键
     * @return 结果
     */
    int deletePaAnalysisConfigById(Long id);

    /**
     * 批量删除PA平台分析配置;PA平台分析配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deletePaAnalysisConfigByIds(Long[] ids);
}
