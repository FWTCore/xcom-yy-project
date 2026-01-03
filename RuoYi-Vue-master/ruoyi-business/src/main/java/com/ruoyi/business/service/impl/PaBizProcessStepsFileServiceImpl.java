package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.entity.PaBizProcessStepsFileDO;
import com.ruoyi.business.domain.model.PaBizProcessStepsFile;
import com.ruoyi.business.mapper.PaBizProcessStepsFileMapper;
import com.ruoyi.business.service.PaBizProcessStepsFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * PA平台业务办理步骤文件;PA平台业务办理步骤文件Service业务层处理
 *
 * @author xcom
 * @date 2026/1/3
 */

@Service
public class PaBizProcessStepsFileServiceImpl implements PaBizProcessStepsFileService {
    @Resource
    private PaBizProcessStepsFileMapper paBizProcessStepsFileMapper;

    /**
     * 查询PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param id PA平台业务办理步骤文件;PA平台业务办理步骤文件主键
     * @return PA平台业务办理步骤文件;PA平台业务办理步骤文件
     */
    @Override
    public PaBizProcessStepsFileDO selectPaBizProcessStepsFileById(Long id) {
        return paBizProcessStepsFileMapper.selectPaBizProcessStepsFileById(id);
    }

    /**
     * 查询PA平台业务办理步骤文件;PA平台业务办理步骤文件列表
     *
     * @param paBizProcessStepsFile PA平台业务办理步骤文件;PA平台业务办理步骤文件
     * @return PA平台业务办理步骤文件;PA平台业务办理步骤文件
     */
    @Override
    public List<PaBizProcessStepsFileDO> selectPaBizProcessStepsFileList(PaBizProcessStepsFile paBizProcessStepsFile) {
        return paBizProcessStepsFileMapper.selectPaBizProcessStepsFileList(paBizProcessStepsFile);
    }

    /**
     * 新增PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param paBizProcessStepsFile PA平台业务办理步骤文件;PA平台业务办理步骤文件
     * @return 结果
     */
    @Override
    public int insertPaBizProcessStepsFile(PaBizProcessStepsFile paBizProcessStepsFile) {
        return paBizProcessStepsFileMapper.insertPaBizProcessStepsFile(paBizProcessStepsFile);
    }

    /**
     * 修改PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param paBizProcessStepsFile PA平台业务办理步骤文件;PA平台业务办理步骤文件
     * @return 结果
     */
    @Override
    public int updatePaBizProcessStepsFile(PaBizProcessStepsFile paBizProcessStepsFile) {
        return paBizProcessStepsFileMapper.updatePaBizProcessStepsFile(paBizProcessStepsFile);
    }

    /**
     * 批量删除PA平台业务办理步骤文件;PA平台业务办理步骤文件
     *
     * @param ids 需要删除的PA平台业务办理步骤文件;PA平台业务办理步骤文件主键
     * @return 结果
     */
    @Override
    public int deletePaBizProcessStepsFileByIds(Long[] ids) {
        return paBizProcessStepsFileMapper.deletePaBizProcessStepsFileByIds(ids);
    }

    /**
     * 删除PA平台业务办理步骤文件;PA平台业务办理步骤文件信息
     *
     * @param id PA平台业务办理步骤文件;PA平台业务办理步骤文件主键
     * @return 结果
     */
    @Override
    public int deletePaBizProcessStepsFileById(Long id) {
        return paBizProcessStepsFileMapper.deletePaBizProcessStepsFileById(id);
    }
}
