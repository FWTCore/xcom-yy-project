package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.entity.PaBizOrchestrationDO;
import com.ruoyi.business.domain.model.PaBizOrchestration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PA平台业务编排;PA平台业务编排Mapper接口
 *
 * @author xcom
 * @date 2026/1/3
 */

@Mapper
public interface PaBizOrchestrationMapper {
    /**
     * 查询PA平台业务编排;PA平台业务编排
     *
     * @param id PA平台业务编排;PA平台业务编排主键
     * @return PA平台业务编排;PA平台业务编排
     */
    PaBizOrchestrationDO selectPaBizOrchestrationById(Long id);

    /**
     * 查询PA平台业务编排;PA平台业务编排列表
     *
     * @param paBizOrchestration PA平台业务编排;PA平台业务编排
     * @return PA平台业务编排;PA平台业务编排集合
     */
    List<PaBizOrchestrationDO> selectPaBizOrchestrationList(PaBizOrchestration paBizOrchestration);

    /**
     * 新增PA平台业务编排;PA平台业务编排
     *
     * @param paBizOrchestration PA平台业务编排;PA平台业务编排
     * @return 结果
     */
    int insertPaBizOrchestration(PaBizOrchestration paBizOrchestration);

    /**
     * 修改PA平台业务编排;PA平台业务编排
     *
     * @param paBizOrchestration PA平台业务编排;PA平台业务编排
     * @return 结果
     */
    int updatePaBizOrchestration(PaBizOrchestration paBizOrchestration);

    /**
     * 删除PA平台业务编排;PA平台业务编排
     *
     * @param id PA平台业务编排;PA平台业务编排主键
     * @return 结果
     */
    int deletePaBizOrchestrationById(Long id);

    /**
     * 批量删除PA平台业务编排;PA平台业务编排
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deletePaBizOrchestrationByIds(Long[] ids);

}
