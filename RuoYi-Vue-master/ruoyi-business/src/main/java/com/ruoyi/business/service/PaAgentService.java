package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.PaAgentDO;
import com.ruoyi.business.domain.model.PaAgent;
import com.ruoyi.business.domain.model.response.PaAgentVO;

import java.util.List;

/**
 * PA平台智能体;PA平台智能体Service接口
 *
 * @author xcom
 * @date 2026/1/2
 */

public interface PaAgentService {
    /**
     * 查询PA平台智能体;PA平台智能体
     *
     * @param id PA平台智能体;PA平台智能体主键
     * @return PA平台智能体;PA平台智能体
     */
    PaAgentDO selectPaAgentById(Long id);

    /**
     * 查询PA平台智能体;PA平台智能体列表
     *
     * @param paAgent PA平台智能体;PA平台智能体
     * @return PA平台智能体;PA平台智能体集合
     */
    List<PaAgentVO> selectPaAgentList(PaAgent paAgent);

    /**
     * 新增PA平台智能体;PA平台智能体
     *
     * @param paAgent PA平台智能体;PA平台智能体
     * @return 结果
     */
    int insertPaAgent(PaAgent paAgent);

    /**
     * 修改PA平台智能体;PA平台智能体
     *
     * @param paAgent PA平台智能体;PA平台智能体
     * @return 结果
     */
    int updatePaAgent(PaAgent paAgent);

    /**
     * 批量删除PA平台智能体;PA平台智能体
     *
     * @param ids 需要删除的PA平台智能体;PA平台智能体主键集合
     * @return 结果
     */
    int deletePaAgentByIds(Long[] ids);

    /**
     * 删除PA平台智能体;PA平台智能体信息
     *
     * @param id PA平台智能体;PA平台智能体主键
     * @return 结果
     */
    int deletePaAgentById(Long id);
}
