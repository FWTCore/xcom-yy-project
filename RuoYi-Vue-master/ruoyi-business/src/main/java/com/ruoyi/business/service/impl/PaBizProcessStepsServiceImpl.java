package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.PaBizProcessStepsDO;
import com.ruoyi.business.domain.model.PaBizProcessSteps;
import com.ruoyi.business.domain.model.response.PaBizProcessStepsVO;
import com.ruoyi.business.mapper.PaBizProcessStepsMapper;
import com.ruoyi.business.service.PaBizProcessStepsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PA平台业务办理步骤;PA平台业务办理步骤Service业务层处理
 *
 * @author xcom
 * @date 2026/1/3
 */

@Service
public class PaBizProcessStepsServiceImpl implements PaBizProcessStepsService {
    @Resource
    private PaBizProcessStepsMapper paBizProcessStepsMapper;

    /**
     * 查询PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param id PA平台业务办理步骤;PA平台业务办理步骤主键
     * @return PA平台业务办理步骤;PA平台业务办理步骤
     */
    @Override
    public PaBizProcessStepsDO selectPaBizProcessStepsById(Long id) {
        return paBizProcessStepsMapper.selectPaBizProcessStepsById(id);
    }

    /**
     * 查询PA平台业务办理步骤;PA平台业务办理步骤列表
     *
     * @param paBizProcessSteps PA平台业务办理步骤;PA平台业务办理步骤
     * @return PA平台业务办理步骤;PA平台业务办理步骤
     */
    @Override
    public List<PaBizProcessStepsVO> selectPaBizProcessStepsList(PaBizProcessSteps paBizProcessSteps) {
        return paBizProcessStepsMapper.selectPaBizProcessStepsList(paBizProcessSteps);
    }

    /**
     * 新增PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param paBizProcessSteps PA平台业务办理步骤;PA平台业务办理步骤
     * @return 结果
     */
    @Override
    public int insertPaBizProcessSteps(PaBizProcessSteps paBizProcessSteps) {
        return paBizProcessStepsMapper.insertPaBizProcessSteps(paBizProcessSteps);
    }

    /**
     * 修改PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param paBizProcessSteps PA平台业务办理步骤;PA平台业务办理步骤
     * @return 结果
     */
    @Override
    public int updatePaBizProcessSteps(PaBizProcessSteps paBizProcessSteps) {
        return paBizProcessStepsMapper.updatePaBizProcessSteps(paBizProcessSteps);
    }

    /**
     * 批量删除PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param ids 需要删除的PA平台业务办理步骤;PA平台业务办理步骤主键
     * @return 结果
     */
    @Override
    public int deletePaBizProcessStepsByIds(Long[] ids) {
        return paBizProcessStepsMapper.deletePaBizProcessStepsByIds(ids);
    }

    /**
     * 删除PA平台业务办理步骤;PA平台业务办理步骤信息
     *
     * @param id PA平台业务办理步骤;PA平台业务办理步骤主键
     * @return 结果
     */
    @Override
    public int deletePaBizProcessStepsById(Long id) {
        return paBizProcessStepsMapper.deletePaBizProcessStepsById(id);
    }
}
