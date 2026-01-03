package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.PaBizOrchestrationDO;
import com.ruoyi.business.domain.model.PaBizOrchestration;
import com.ruoyi.business.domain.model.response.PaBizOrchestrationVO;
import com.ruoyi.business.mapper.PaBizOrchestrationMapper;
import com.ruoyi.business.service.PaBizOrchestrationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PA平台业务编排;PA平台业务编排Service业务层处理
 *
 * @author xcom
 * @date 2026/1/3
 */

@Service
public class PaBizOrchestrationServiceImpl implements PaBizOrchestrationService {
    @Resource
    private PaBizOrchestrationMapper paBizOrchestrationMapper;

    /**
     * 查询PA平台业务编排;PA平台业务编排
     *
     * @param id PA平台业务编排;PA平台业务编排主键
     * @return PA平台业务编排;PA平台业务编排
     */
    @Override
    public PaBizOrchestrationDO selectPaBizOrchestrationById(Long id) {
        return paBizOrchestrationMapper.selectPaBizOrchestrationById(id);
    }

    /**
     * 查询PA平台业务编排;PA平台业务编排列表
     *
     * @param paBizOrchestration PA平台业务编排;PA平台业务编排
     * @return PA平台业务编排;PA平台业务编排
     */
    @Override
    public List<PaBizOrchestrationVO> selectPaBizOrchestrationList(PaBizOrchestration paBizOrchestration) {
        return paBizOrchestrationMapper.selectPaBizOrchestrationList(paBizOrchestration);
    }

    /**
     * 新增PA平台业务编排;PA平台业务编排
     *
     * @param paBizOrchestration PA平台业务编排;PA平台业务编排
     * @return 结果
     */
    @Override
    public int insertPaBizOrchestration(PaBizOrchestration paBizOrchestration) {
        return paBizOrchestrationMapper.insertPaBizOrchestration(paBizOrchestration);
    }

    /**
     * 修改PA平台业务编排;PA平台业务编排
     *
     * @param paBizOrchestration PA平台业务编排;PA平台业务编排
     * @return 结果
     */
    @Override
    public int updatePaBizOrchestration(PaBizOrchestration paBizOrchestration) {
        return paBizOrchestrationMapper.updatePaBizOrchestration(paBizOrchestration);
    }

    /**
     * 批量删除PA平台业务编排;PA平台业务编排
     *
     * @param ids 需要删除的PA平台业务编排;PA平台业务编排主键
     * @return 结果
     */
    @Override
    public int deletePaBizOrchestrationByIds(Long[] ids) {
        return paBizOrchestrationMapper.deletePaBizOrchestrationByIds(ids);
    }

    /**
     * 删除PA平台业务编排;PA平台业务编排信息
     *
     * @param id PA平台业务编排;PA平台业务编排主键
     * @return 结果
     */
    @Override
    public int deletePaBizOrchestrationById(Long id) {
        return paBizOrchestrationMapper.deletePaBizOrchestrationById(id);
    }
}
