package com.ruoyi.business.service;

import com.ruoyi.business.domain.entity.PaBizProcessStepsFileDO;
import com.ruoyi.business.domain.model.PaBizProcessStepsFile;

import java.util.List;

/**
 * PA平台业务办理步骤文件;PA平台业务办理步骤文件Service接口
 *
 * @author xcom
 * @date 2026/1/3
 */

public interface PaBizProcessStepsFileService {
    /**
     * 查询PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param id PA平台业务办理步骤文件;PA平台业务办理步骤文件主键
     * @return PA平台业务办理步骤文件;PA平台业务办理步骤文件
     */
    PaBizProcessStepsFileDO selectPaBizProcessStepsFileById(Long id);

    /**
     * 查询PA平台业务办理步骤文件;PA平台业务办理步骤文件列表
     *
     * @param paBizProcessStepsFile PA平台业务办理步骤文件;PA平台业务办理步骤文件
     * @return PA平台业务办理步骤文件;PA平台业务办理步骤文件集合
     */
    List<PaBizProcessStepsFileDO> selectPaBizProcessStepsFileList(PaBizProcessStepsFile paBizProcessStepsFile);

    /**
     * 新增PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param paBizProcessStepsFile PA平台业务办理步骤文件;PA平台业务办理步骤文件
     * @return 结果
     */
    int insertPaBizProcessStepsFile(PaBizProcessStepsFile paBizProcessStepsFile);

    /**
     * 修改PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param paBizProcessStepsFile PA平台业务办理步骤文件;PA平台业务办理步骤文件
     * @return 结果
     */
    int updatePaBizProcessStepsFile(PaBizProcessStepsFile paBizProcessStepsFile);

    /**
     * 批量删除PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param ids 需要删除的PA平台业务办理步骤文件;PA平台业务办理步骤文件主键集合
     * @return 结果
     */
    int deletePaBizProcessStepsFileByIds(Long[] ids);

    /**
     * 删除PA平台业务办理步骤文件;PA平台业务办理步骤文件信息
     *
     * @param id PA平台业务办理步骤文件;PA平台业务办理步骤文件主键
     * @return 结果
     */
    int deletePaBizProcessStepsFileById(Long id);
}
