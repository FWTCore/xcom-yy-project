package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.PaAgentDO;
import com.ruoyi.business.domain.model.PaAgent;
import com.ruoyi.business.domain.model.response.PaAgentVO;
import com.ruoyi.business.mapper.PaAgentMapper;
import com.ruoyi.business.service.PaAgentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PA平台智能体;PA平台智能体Service业务层处理
 *
 * @author xcom
 * @date 2026/1/2
 */

@Service
public class PaAgentServiceImpl implements PaAgentService {
    @Resource
    private PaAgentMapper paAgentMapper;

    /**
     * 查询PA平台智能体;PA平台智能体
     *
     * @param id PA平台智能体;PA平台智能体主键
     * @return PA平台智能体;PA平台智能体
     */
    @Override
    public PaAgentDO selectPaAgentById(Long id) {
        return paAgentMapper.selectPaAgentById(id);
    }

    /**
     * 查询PA平台智能体;PA平台智能体列表
     *
     * @param paAgent PA平台智能体;PA平台智能体
     * @return PA平台智能体;PA平台智能体
     */
    @Override
    public List<PaAgentVO> selectPaAgentList(PaAgent paAgent) {
        return paAgentMapper.selectPaAgentList(paAgent);
    }

    /**
     * 新增PA平台智能体;PA平台智能体
     *
     * @param paAgent PA平台智能体;PA平台智能体
     * @return 结果
     */
    @Override
    public int insertPaAgent(PaAgent paAgent) {
        return paAgentMapper.insertPaAgent(paAgent);
    }

    /**
     * 修改PA平台智能体;PA平台智能体
     *
     * @param paAgent PA平台智能体;PA平台智能体
     * @return 结果
     */
    @Override
    public int updatePaAgent(PaAgent paAgent) {
        return paAgentMapper.updatePaAgent(paAgent);
    }

    /**
     * 批量删除PA平台智能体;PA平台智能体
     *
     * @param ids 需要删除的PA平台智能体;PA平台智能体主键
     * @return 结果
     */
    @Override
    public int deletePaAgentByIds(Long[] ids) {
        return paAgentMapper.deletePaAgentByIds(ids);
    }

    /**
     * 删除PA平台智能体;PA平台智能体信息
     *
     * @param id PA平台智能体;PA平台智能体主键
     * @return 结果
     */
    @Override
    public int deletePaAgentById(Long id) {
        return paAgentMapper.deletePaAgentById(id);
    }
}
