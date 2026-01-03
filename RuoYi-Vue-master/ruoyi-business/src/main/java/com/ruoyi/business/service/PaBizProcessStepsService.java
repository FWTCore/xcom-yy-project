package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.PaBizProcessStepsDO;
import com.ruoyi.business.domain.model.PaBizProcessSteps;
import com.ruoyi.business.domain.model.response.PaBizProcessStepsVO;

import java.util.List;

/**
 * PA平台业务办理步骤;PA平台业务办理步骤Service接口
 *
 * @author xcom
 * @date 2026/1/3
 */

public interface PaBizProcessStepsService {
    /**
     * 查询PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param id PA平台业务办理步骤;PA平台业务办理步骤主键
     * @return PA平台业务办理步骤;PA平台业务办理步骤
     */
    PaBizProcessStepsDO selectPaBizProcessStepsById(Long id);

    /**
     * 查询PA平台业务办理步骤;PA平台业务办理步骤列表
     *
     * @param paBizProcessSteps PA平台业务办理步骤;PA平台业务办理步骤
     * @return PA平台业务办理步骤;PA平台业务办理步骤集合
     */
    List<PaBizProcessStepsVO> selectPaBizProcessStepsList(PaBizProcessSteps paBizProcessSteps);

    /**
     * 新增PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param paBizProcessSteps PA平台业务办理步骤;PA平台业务办理步骤
     * @return 结果
     */
    int insertPaBizProcessSteps(PaBizProcessSteps paBizProcessSteps);

    /**
     * 修改PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param paBizProcessSteps PA平台业务办理步骤;PA平台业务办理步骤
     * @return 结果
     */
    int updatePaBizProcessSteps(PaBizProcessSteps paBizProcessSteps);

    /**
     * 批量删除PA平台业务办理步骤;PA平台业务办理步骤
     *
     * @param ids 需要删除的PA平台业务办理步骤;PA平台业务办理步骤主键集合
     * @return 结果
     */
    int deletePaBizProcessStepsByIds(Long[] ids);

    /**
     * 删除PA平台业务办理步骤;PA平台业务办理步骤信息
     *
     * @param id PA平台业务办理步骤;PA平台业务办理步骤主键
     * @return 结果
     */
    int deletePaBizProcessStepsById(Long id);
}
